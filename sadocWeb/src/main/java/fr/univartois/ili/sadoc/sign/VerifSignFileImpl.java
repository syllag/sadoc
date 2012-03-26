package fr.univartois.ili.sadoc.sign;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.security.cert.CertificateException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedDataParser;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Store;

public class VerifSignFileImpl implements IVerifSignFile {
	
	public boolean verifSignFile(byte[] fileToSign, byte[] signature) throws CMSException, IOException, OperatorCreationException, CertificateException {
		
		boolean signatureStatus;
		
		// Use of Bouncy Castle library
		Security.addProvider(new BouncyCastleProvider());
		
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
