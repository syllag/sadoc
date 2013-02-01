package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Signature implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private Document document;
	
	private Owner owner;
	
	private Competence competence;

	private Certificate certificate;
	
	private Date creationDate;

	/****************************************/

	public Signature (){}
	
	public Signature (Document document, Owner owner, Competence competence, Certificate certificate) {
		this.document=document;
		this.owner=owner;
		this.competence=competence;
		this.certificate=certificate;
	}
	
	/****************************************/

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public Date getCreationDate() {
		return (Date) creationDate.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = (Date) creationDate.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
