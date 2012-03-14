package fr.univartois.ili.sadoc.sign;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.X509Certificate;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;
import org.bouncycastle.asn1.x509.*;

public class VerifSignFileImpl implements IVerifSignFile {
	
	public boolean verifSignFile(byte[] fileToSign, byte[] signature) throws CMSException, IOException, OperatorCreationException, CertificateException {
		
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
		
        // true : the file to verify has not been modified
        // false : the file to verify has been modified
		return signatureStatus;
	}

}
