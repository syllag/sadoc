package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;

import fr.univartois.ili.sadoc.metier.ws.certificate.BaseCertificate;
import fr.univartois.ili.sadoc.metier.ws.certificate.CertificatePublicKey;

/**
 * 
 * 
 */
public class Certificate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private BaseCertificate certificate;
	private PrivateKey privateKey;

	private Date dateValidity;

	private long idOwner;
	private List<Signature> signatures;

	/************************************************/

	public Certificate() {
	}

	public Certificate(BaseCertificate certificate, PrivateKey privateKey) {
		this.certificate = certificate;
		this.privateKey = privateKey;
	}

	public Certificate(PublicKey publicKey, PrivateKey privateKey) {
		this.certificate = new CertificatePublicKey(publicKey);
		this.privateKey = privateKey;
	}

	public Certificate(PublicKey publicKey, PrivateKey privateKey, long idOwner) {
		this(publicKey, privateKey);
		this.idOwner = idOwner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BaseCertificate getCertificate() {
		return certificate;
	}

	public void setCertificate(BaseCertificate certificate) {
		this.certificate = certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public Date getDateValidity() {
		return dateValidity;
	}

	public void setDateValidity(Date dateValidity) {
		this.dateValidity = dateValidity;
	}

	public long getIdOwner() {
		return idOwner;
	}

	public void setOwner(Owner o) {
		idOwner = o.getId();
	}
	
	public void setIdOwner(long idOwner) {
		this.idOwner = idOwner;
	}

	public List<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}

	/************************************************/

	
	

}
