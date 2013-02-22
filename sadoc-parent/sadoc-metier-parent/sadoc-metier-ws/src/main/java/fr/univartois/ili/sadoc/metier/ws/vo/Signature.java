package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;

import fr.univartois.ili.sadoc.dao.entities.Certificate;
import fr.univartois.ili.sadoc.dao.entities.Document;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Signature implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private Document document;
	
	private Certificate certificate;
	
	private Date dateSignature;
	

	/****************************************/

	public Signature (){}
	
	public Signature (Document document, Certificate certificate) {
		this.document=document;	
		this.certificate=certificate;
	}
	
	/****************************************/

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Date getCreationDate() {
		return (Date) dateSignature.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.dateSignature = (Date) creationDate.clone();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
