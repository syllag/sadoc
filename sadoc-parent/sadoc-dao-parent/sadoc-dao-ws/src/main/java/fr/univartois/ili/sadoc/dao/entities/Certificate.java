package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Certificate implements Serializable {

	
	private static final long serialVersionUID = -9114570813117740742L;

	@Id
	private long id;
	
	private Date dateValidity;
	private Blob certificate;
	private Blob privateKey;
	@Enumerated(EnumType.STRING)
	private CertificateType certificateType;
	
	//rel
	@ManyToOne
	private OwnerWS ownerWs;
	@OneToMany(mappedBy="certificate")
	private List<Signature> signatures ;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the dateValidity
	 */
	public Date getDateValidity() {
		return dateValidity;
	}

	/**
	 * @param dateValidity the dateValidity to set
	 */
	public void setDateValidity(Date dateValidity) {
		this.dateValidity = dateValidity;
	}

	/**
	 * @return the certificate
	 */
	public Blob getCertificate() {
		return certificate;
	}

	/**
	 * @param certificate the certificate to set
	 */
	public void setCertificate(Blob certificate) {
		this.certificate = certificate;
	}

	/**
	 * @return the privateKey
	 */
	public Blob getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(Blob privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the certificateType
	 */
	public CertificateType getCertificateType() {
		return certificateType;
	}

	/**
	 * @param certificateType the certificateType to set
	 */
	public void setCertificateType(CertificateType certificateType) {
		this.certificateType = certificateType;
	}

	/**
	 * @return the ownerWs
	 */
	public OwnerWS getOwnerWs() {
		return ownerWs;
	}

	/**
	 * @param ownerWs the ownerWs to set
	 */
	public void setOwnerWs(OwnerWS ownerWs) {
		this.ownerWs = ownerWs;
	}

	/**
	 * @return the signatures
	 */
	public List<Signature> getSignatures() {
		return signatures;
	}

	/**
	 * @param signatures the signatures to set
	 */
	public void setSignatures(List<Signature> signatures) {
		this.signatures = signatures;
	}
	
}
