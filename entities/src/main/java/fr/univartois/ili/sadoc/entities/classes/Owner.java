package fr.univartois.ili.sadoc.entities.classes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="owner")
public class Owner implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	// TODO Numéro de SECU ou Numéro carte séjour
	
	private String firstName;
	private String lastName;
	private String mail;
	
	@OneToMany
	private List<Certificate> certificates;
	
	/************************************************/
	
	public Owner(){}
	
	public Owner(String firstName, String lastName, String mail) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.mail=mail;
	}
	
	public Owner(String firstName, String lastName, String mail, List<Certificate> certificates) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.mail=mail;
		this.certificates=certificates;
	}
	
	/************************************************/

	public int compareTo(Object other) { 
	      String name1 = ((Owner) other).getLastName()+((Owner) other).getFirstName(); 
	      String name2 = this.getLastName()+this.getFirstName(); 
	      return name2.compareTo(name1);
	   } 
	
	/************************************************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(ArrayList<Certificate> certificates) {
		this.certificates = certificates;
	}

}