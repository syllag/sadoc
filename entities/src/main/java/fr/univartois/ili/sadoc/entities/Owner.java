package fr.univartois.ili.sadoc.entities;

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
public class Owner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// TODO Numéro de SECU ou Numéro carte séjour

	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	private String address;
	private String zipCode;
	private String town;
	private String phone;

	@OneToMany
	private List<Resume> resumes;

	@OneToMany
	private List<Degree> degrees;

	/************************************************/

	public Owner(){}
	
	public Owner(String firstName,String lastName,String mail, String password, String address,String zipCode,String town, String phone){
		this.firstName=firstName;
		this.lastName=lastName;
		this.mail=mail;
		this.password=password;
		this.address=address;
		this.zipCode=zipCode;
		this.town=town;
		this.phone=phone;
	}
	
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

	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean equals(Owner owner) {
		return this.id == owner.getId();
	}

}