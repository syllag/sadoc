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
 * @author Kenny Cormont <kennycormontili at gmail.com>
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
	
	private String mail;
	
	private int id_competence;
	
	@OneToOne
	private Certificate certificate;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	/****************************************/

	public Signature (){}
	
	public Signature (Document document, String mail, int id_competence, Certificate certificate) {
		this.document=document;
		this.mail=mail;
		this.id_competence=id_competence;
		this.certificate=certificate;
	}
	
	/****************************************/

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getId_competence() {
		return id_competence;
	}

	public void setId_competence(int id_competence) {
		this.id_competence = id_competence;
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
