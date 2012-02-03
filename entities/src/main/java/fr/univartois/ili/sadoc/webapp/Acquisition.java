package fr.univartois.ili.sadoc.webapp;

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
public class Acquisition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private User user;
	
	@Id
	@OneToOne
	private Document document;
	
	@Id
	@OneToOne
	private Competence competence;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	/************************************************/

	public Acquisition() {}
	
	/************************************************/
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
