package fr.univartois.ili.sadoc.webapp;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	// TODO Numéro de SECU ou Numéro carte séjour
	
	private String nom;
	private String prenom;
	private String mail;
	private String password;
	
	@OneToMany
	private List<CV> cvs;
	
	/************************************************/
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	@OneToMany
	private List<Degree> degrees;

	/************************************************/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<CV> getCvs() {
		return cvs;
	}

	public void setCvs(List<CV> cvs) {
		this.cvs = cvs;
	}

}