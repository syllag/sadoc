package fr.univartois.ili.sadoc.entitiesws.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="SIGNATURE")
public class Signature implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private Document document;
	
	@OneToOne
	private Owner owner;
	
	@OneToOne
	private Competence competence;
	
	@OneToOne
	private Certificate certificate;
	
	@Temporal(TemporalType.DATE)
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
