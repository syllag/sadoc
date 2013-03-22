package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Signature implements Serializable {
	
	private static final long serialVersionUID = 4108334434685842141L;
	
	@Id
	@GeneratedValue
	private long id;
	@Temporal(TemporalType.DATE)
	private Date dateSignature;
	
	//rel
	@ManyToOne
	private Certificate certificate;
	@ManyToOne
	private Document document;
	
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
	 * @return the dateSignature
	 */
	public Date getDateSignature() {
		return dateSignature;
	}
	/**
	 * @param dateSignature the dateSignature to set
	 */
	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}
	/**
	 * @return the certificate
	 */
	public Certificate getCertificate() {
		return certificate;
	}
	/**
	 * @param certificate the certificate to set
	 */
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	
}