package fr.univartois.ili.sadoc.dao.entities;

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
 * @author damien wattiez <damien.wattiez at gmail.com>
 * @author Kenny Cormont <kennycormontili at gmail.com>
 * 
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
	private Document document;
	
	@OneToOne
	private int id_competence;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	/************************************************/

	public Acquisition() {}
	
	public Acquisition(Document document, int id_competence,Date creationDate){
		this.document=document;
		this.id_competence=id_competence;
		this.creationDate=creationDate;
	}
	
	/************************************************/

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public int getId_ompetence() {
		return id_competence;
	}

	public void setId_competence(int id_competence) {
		this.id_competence = id_competence;
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
