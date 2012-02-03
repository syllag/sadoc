package fr.univartois.ili.sadoc.webservice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
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
	@OneToOne
	private Document document;
	@Id
	@OneToOne
	private User user;
	@Id
	@OneToOne
	private Competence competence;
	@Id
	@OneToOne
	private Certificate certificate;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	/****************************************/
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
