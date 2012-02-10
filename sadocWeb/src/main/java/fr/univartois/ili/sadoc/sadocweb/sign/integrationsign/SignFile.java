package fr.univartois.ili.sadoc.sadocweb.sign.integrationsign;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
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
import fr.univartois.ili.sadoc.entities.Owner;

public class SignFile {
	
	private static final String algorithm  = "RSA";
	private static final int NBBITS = 1024;
	
	/**
	 * Generate a key pair (public and private).
	 * 
	 * @return key pair.
	 */
	private KeyPair generateRSAKeyPair() throws Exception {
	    KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm, "BC");
	    kpg.initialize(NBBITS, new SecureRandom());
	    return kpg.generateKeyPair();
	  }
	
	/**
	 * Create a X509 certificate.
	 * 
	 * @param KeyPair
	 * @param Owner
	 *            
	 * @return X509 certificate.
	 */
	private X509Certificate createCertificate(KeyPair pair, Owner owner) throws Exception {
		// Use Bouncy Castle library
		Security.addProvider(new BouncyCastleProvider());

		// Informations about the self-signed certificate
		X500NameBuilder certifBuilder = new X500NameBuilder(BCStyle.INSTANCE);
		certifBuilder.addRDN(BCStyle.OU, owner.getFirstName());
		certifBuilder.addRDN(BCStyle.O, owner.getLastName());
		certifBuilder.addRDN(BCStyle.CN, "Faculté Perrin");
		Date notBefore = new Date(System.currentTimeMillis() - 1);
		Date notAfter = new Date(System.currentTimeMillis() + 10*365*24*60*60*1000l);
		BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());

		// Generate the self-signed certificate
		X509v3CertificateBuilder certifGen = new JcaX509v3CertificateBuilder(certifBuilder.build(),
				serial, notBefore, notAfter, certifBuilder.build(), pair.getPublic());
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1WithRSAEncryption")
				.setProvider("BC").build(pair.getPrivate());
		X509Certificate certX509 = new JcaX509CertificateConverter().setProvider("BC")
				.getCertificate(certifGen.build(sigGen));
		
		return certX509;
	}
	
	/**
	 * Sign a document.
	 * 
	 * @param file to sign
	 * @param Owner
	 *            
	 * @return file P7S.
	 */
	public byte[] signDocument(byte[] fileToSign, Owner owner) throws Exception {
        
		// Use Bouncy Castle library
		Security.addProvider(new BouncyCastleProvider());
		
		// Generate private and public keys
		KeyPair keys = generateRSAKeyPair();
				
		// recovery private key and certificate X509
		PrivateKey privateKey = keys.getPrivate();
		X509Certificate certX509 = createCertificate(keys,owner);
		
		// declaration of certificates store
		// for SAdoc, one certificate is used but declaration of
		// certificates store is necessary
		List<X509Certificate> certifList = new ArrayList<X509Certificate>();
		certifList.add(certX509);
		Store certifs = new JcaCertStore(certifList);
		
		// save the user's file in a CMSTypedData object
	    CMSTypedData  originalFile = new CMSProcessableByteArray(fileToSign);
	    // Initialize p7s file
	    CMSSignedDataGenerator fileP7S = new CMSSignedDataGenerator();
	    // calculate hash of user's file
	    ContentSigner sha1Signature = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(privateKey);

	    // add several informations (sha1 hash and certificate X509) in p7s file
	    fileP7S.addSignerInfoGenerator(
	                new JcaSignerInfoGeneratorBuilder(
	                     new JcaDigestCalculatorProviderBuilder().setProvider("BC").build())
	                     .build(sha1Signature, certX509));
	    fileP7S.addCertificates(certifs);

	    // create the p7s file
	    // false option : indicate original file is not stored in p7s file
	    // true option : indicate original file is stored in p7s file 
	    CMSSignedData sigData = fileP7S.generate(originalFile, false);
	    
	    return sigData.getEncoded();
	}
	
	/**
	 * verify the signature of a document.
	 * 
	 * @param file to verify
	 * @param file P7S
	 *            
	 * @return true or false
	 *   true : the file to verify has not been modified
	 *   false : the file to verify has been modified
	 */
	public boolean verifSignatureDocument(byte[] fileToSign, byte[] signature) throws CMSException, IOException, OperatorCreationException, CertificateException {
		
		boolean signatureStatus;
		
		// sign the file to verify
		InputStream userFile  = new ByteArrayInputStream(fileToSign);
		CMSSignedDataParser signedFile = new CMSSignedDataParser(new CMSTypedStream(userFile),signature);
        CMSTypedStream signedContent = signedFile.getSignedContent();           
        signedContent.drain();
        
        // recovery certificate X509 and SHA1 hash
        Store certStore = signedFile.getCertificates(); 
        SignerInformationStore signers = signedFile.getSignerInfos();    
        SignerInformation signer = (SignerInformation)signers.getSigners().iterator().next();
        X509CertificateHolder certHolder = (X509CertificateHolder)certStore.getMatches(signer.getSID()).iterator().next(); 
        
        // verify the hash
        try {
        	signatureStatus = signer.verify(new 
			        	    JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(certHolder));
		} catch (CMSException e) {
			signatureStatus=false;
		}	

		return signatureStatus;
	}
	
}
