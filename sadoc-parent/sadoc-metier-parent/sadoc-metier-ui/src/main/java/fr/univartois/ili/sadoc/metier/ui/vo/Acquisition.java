package fr.univartois.ili.sadoc.metier.ui.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * @autor damien wattiez <damien.wattiez at gmail.com>
 */
public class Acquisition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private Owner owner;
	
	private Document document;
	
	private Competence competence;
	
	private Date creationDate;
	
	/************************************************/

	public Acquisition() {}
	
	public Acquisition(Owner owner,Document document, Competence competence,Date creationDate){
		this.owner=owner;
		this.document=document;
		this.competence=competence;
		this.creationDate=creationDate;
	}
	
	/************************************************/
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
