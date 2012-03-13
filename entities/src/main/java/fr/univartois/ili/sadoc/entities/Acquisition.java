package fr.univartois.ili.sadoc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * @autor damien wattiez <damien.wattiez at gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Acquisition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private Owner owner;
	
	@OneToOne
	private Document document;
	
	@OneToOne
	private Competence competence;
	
	@Temporal(TemporalType.DATE)
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
