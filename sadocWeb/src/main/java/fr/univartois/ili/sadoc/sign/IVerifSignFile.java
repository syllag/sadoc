package fr.univartois.ili.sadoc.sign;

import java.io.IOException;
import java.security.cert.CertificateException;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.OperatorCreationException;

public interface IVerifSignFile {
	
  public boolean verifSignFile(byte[] fileToSign, byte[] signature) throws CMSException, IOException, OperatorCreationException, CertificateException;

}
