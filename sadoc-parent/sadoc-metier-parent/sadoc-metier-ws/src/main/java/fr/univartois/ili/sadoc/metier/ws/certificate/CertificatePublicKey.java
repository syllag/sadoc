package fr.univartois.ili.sadoc.metier.ws.certificate;

import java.security.PublicKey;
import java.util.Date;

import fr.univartois.ili.sadoc.dao.entities.CertificateType;

public class CertificatePublicKey implements BaseCertificate {

	private PublicKey key;
	
	
	
	public CertificatePublicKey(PublicKey key) {
		this.key = key;
	}

	public CertificatePublicKey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Date getDateValidity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicKey getPublicKey() {
		return key;
	}

	@Override
	public CertificateType getType() {
		return CertificateType.PublicKey;
	}

}
