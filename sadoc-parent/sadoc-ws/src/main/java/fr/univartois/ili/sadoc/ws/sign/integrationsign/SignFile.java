package fr.univartois.ili.sadoc.ws.sign.integrationsign;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSSignedDataParser;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;

import fr.univartois.ili.sadoc.metier.ws.certificate.CertificatePublicKey;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

public class SignFile {

	private static final String PROPERTIE_INIT = "initComp";
	private static final String PATH_CERTIF = "pathCertificate";
	private static final String ALGORITHM = "RSA";
	private static final int NBBITS = 1024;
	private static final String TYPEOFCERTIFICATE = "X.509";
	private static final String CYPHER = "Blowfish";
	private static final String CYPHERPARAMETERS = "Blowfish/CBC/PKCS5Padding";
	private static final String FOLDER = ResourceBundle.getBundle(PROPERTIE_INIT).getString(PATH_CERTIF);
//	private static final String CACNRSROOT = "CNRS2.crt";
	private static final String CACNRSSTANDARD = "CNRS2-Standard.crt";
	private static final String CASADOC = "sadoc.pem";
	private static final String SADOCPRIVATEKEY = "sadoc.pk8";

	/**
	 * Generate a key pair (public and private).
	 * 
	 * @return key pair.
	 */
	private KeyPair generateRSAKeyPair() throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, "BC");
		kpg.initialize(NBBITS, new SecureRandom());
		return kpg.generateKeyPair();
	}

	/**
	 * Decrypt the PK8 file.
	 * 
	 * @param Filename
	 *            to decrypt
	 * @param Password
	 * 
	 * @return Decrypted PK8 file.
	 */
	private byte[] decryptPK8File(String filenameToDecrypt, String password) throws IOException, NoSuchAlgorithmException,NoSuchProviderException, NoSuchPaddingException,InvalidKeyException,InvalidAlgorithmParameterException,IllegalBlockSizeException, BadPaddingException {

		File file = new File(filenameToDecrypt);
		byte[] inputBuffer = new byte[(int) file.length()];
		DataInputStream inputFile = new DataInputStream(new FileInputStream(file));
		inputFile.readFully(inputBuffer);
		inputFile.close();

		// Initialization vector (useful for CBC)
		byte[] iv = { (byte) 0xc9, (byte) 0x36, (byte) 0x78, (byte) 0x99,(byte) 0x52, (byte) 0x3e, (byte) 0xea, (byte) 0xf2 };

		// Create a new initialization vector spec
		IvParameterSpec ivps = new IvParameterSpec(iv);
		byte[] key = password.getBytes();
		SecretKey secretKey = new SecretKeySpec(key, CYPHER);

		// Decrypt the file
		Cipher c = Cipher.getInstance(CYPHERPARAMETERS, "BC");
		c.init(Cipher.DECRYPT_MODE, secretKey, ivps);
		return c.doFinal(inputBuffer);
	}

	/**
	 * Read X509 certificate file (CNRS2, CNRS2-Standard and Sadoc).
	 * 
	 * @param certificate
	 *            file
	 * 
	 * 
	 * @return X509 certificate.
	 */
	private X509Certificate ReadCertificateFile(String filename) throws IOException, CertificateException {
		System.out.println("certificat " + filename);
		InputStream inputX509 = new FileInputStream(filename);
		CertificateFactory cfX509 = CertificateFactory.getInstance(TYPEOFCERTIFICATE);
		X509Certificate certX509 = (X509Certificate) cfX509.generateCertificate(inputX509);
		inputX509.close();
		return certX509;
	}

	/**
	 * Read private key (fac) present in PK8 file (decrypted).
	 * 
	 * @param PK8
	 *            file
	 * 
	 * 
	 * @return private key.
	 */
	private PrivateKey readPK8File(byte[] pk8File) throws IOException,NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(pk8File);
		KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
		return kf.generatePrivate(pkcs8);
	}

	/**
	 * Create a X509 certificate for a user.
	 * 
	 * @param Fac
	 *            's X509 certificate
	 * @param Fac
	 *            's private key
	 * @param User
	 *            's keypair
	 * @param User
	 * @param Date
	 *            notBefore
	 * @param Date
	 *            notAfter
	 * 
	 * 
	 * @return X509 certificate.
	 */
	private X509Certificate createCertificate(X509Certificate facCA,PrivateKey facPK, KeyPair keyUser, Owner user, Date notBefore,Date notAfter) throws Exception {
		// Use of Bouncy Castle library
		Security.addProvider(new BouncyCastleProvider());

		// Informations about the user's certificate
		X500NameBuilder certifBuilder = new X500NameBuilder(BCStyle.INSTANCE);
		certifBuilder.addRDN(BCStyle.C, "FR");
		certifBuilder.addRDN(BCStyle.ST, "France");
		certifBuilder.addRDN(BCStyle.L, "LENS");
		certifBuilder.addRDN(BCStyle.O,user.getFirstName() + " " + user.getLastName());
		certifBuilder.addRDN(BCStyle.OU,user.getFirstName() + " " + user.getLastName());
		certifBuilder.addRDN(BCStyle.CN,user.getFirstName() + " " + user.getLastName());

		BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());

		// Generate certificate
		X509v3CertificateBuilder certifGen = new JcaX509v3CertificateBuilder(new X509CertificateHolder(facCA.getEncoded()).getSubject(),serial, notBefore, notAfter, certifBuilder.build(),keyUser.getPublic());

		certifGen.addExtension(X509Extension.subjectKeyIdentifier, false,new SubjectKeyIdentifierStructure(keyUser.getPublic()));
		certifGen.addExtension(X509Extension.basicConstraints, false,new BasicConstraints(false));

		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1WithRSAEncryption").setProvider("BC").build(facPK);
		X509Certificate certX509 = new JcaX509CertificateConverter().setProvider("BC").getCertificate(certifGen.build(sigGen));

		return certX509;
	}

	/**
	 * Give the user's certificate.
	 * 
	 * @param User
	 * 
	 * 
	 * @return X509 certificate.
	 */
	public Certificate GiveCertificateForUser(Owner o) throws Exception {

		Security.addProvider(new BouncyCastleProvider());

		Certificate certif = null;

		if (o.getCertificates().isEmpty()) {
			// Generate private and public keys
			KeyPair userKeys = generateRSAKeyPair();
			certif = new Certificate(userKeys.getPublic(), userKeys.getPrivate(), o.getId());
			o.getCertificates().add(certif);
		} else {
			certif = o.getCertificates().get(0);
		}

		return certif;
	}

	/**
	 * Sign a document.
	 * 
	 * @param file
	 *            to sign
	 * @param Owner
	 * 
	 * @return file P7S.
	 */
	public byte[] signDocument(byte[] fileToSign, Owner owner, Certificate certificate) throws Exception {

		// Use of Bouncy Castle library
		Security.addProvider(new BouncyCastleProvider());

		// Read the private key and the different certificates
		byte[] pk8File = decryptPK8File(FOLDER + SADOCPRIVATEKEY, this.getClass().getSimpleName());
		PrivateKey facPK = readPK8File(pk8File);
		//X509Certificate root = ReadCertificateFile(FOLDER + CACNRSROOT);
		X509Certificate cnrsCA = ReadCertificateFile(FOLDER + CACNRSSTANDARD);
		X509Certificate facCA = ReadCertificateFile(FOLDER + CASADOC);

		// Generate private and public keys
		KeyPair userKeys = new KeyPair(certificate.getCertificate().getPublicKey(), certificate.getPrivateKey());
		X509Certificate certX509 = createCertificate(facCA, facPK, userKeys,owner, facCA.getNotBefore(), facCA.getNotAfter());

		// Declaration of certificates store
		// This store must contain all certificates (CNRS2, CNRS2-Standard,
		// Sadoc and
		// user) to obtain a certificate hierarchy
		List<X509Certificate> certifList = new ArrayList<X509Certificate>();
		certifList.add(certX509);
		certifList.add(facCA);
		certifList.add(cnrsCA);
	//	certifList.add(root);
		Store certifs = new JcaCertStore(certifList);

		// save the user's file in a CMSTypedData object
		CMSTypedData originalFile = new CMSProcessableByteArray(fileToSign);
		// Initialize p7s file
		CMSSignedDataGenerator fileP7S = new CMSSignedDataGenerator();
		// calculate hash of user's file
		ContentSigner sha1Signature = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(userKeys.getPrivate());

		// add several informations (sha1 hash and certificate X509) in p7s file
		fileP7S.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signature, certX509));
		fileP7S.addCertificates(certifs);

		// create the p7s file
		// false option : indicate original file is not stored in p7s file
		// true option : indicate original file is stored in p7s file
		CMSSignedData sigData = fileP7S.generate(originalFile, false);

		// thanks to getEncoded() function, the method returns a byte array
		return sigData.getEncoded();
	}

	/**
	 * verify the signature of a document.
	 * 
	 * @param file
	 *            to verify
	 * @param file
	 *            P7S
	 * 
	 * @return true or false true : the file to verify has not been modified
	 *         false : the file to verify has been modified
	 */
	public boolean verifSignatureDocument(byte[] fileToSign, byte[] signature) throws CMSException, IOException, OperatorCreationException,CertificateException {

		boolean signatureStatus;

		// sign the file to verify
		InputStream userFile = new ByteArrayInputStream(fileToSign);
		CMSSignedDataParser signedFile = new CMSSignedDataParser(new CMSTypedStream(userFile), signature);
		CMSTypedStream signedContent = signedFile.getSignedContent();
		signedContent.drain();

		// recovery certificate X509 and SHA1 hash
		Store certStore = signedFile.getCertificates();
		SignerInformationStore signers = signedFile.getSignerInfos();
		SignerInformation signer = (SignerInformation) signers.getSigners().iterator().next();
		X509CertificateHolder certHolder = (X509CertificateHolder) certStore.getMatches(signer.getSID()).iterator().next();

		// verify the hash
		try {
			signatureStatus = signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(certHolder));
		} catch (CMSException e) {
			signatureStatus = false;
		}

		return signatureStatus;
	}

}
