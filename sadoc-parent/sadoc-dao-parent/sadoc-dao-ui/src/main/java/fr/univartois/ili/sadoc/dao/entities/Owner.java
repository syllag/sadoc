package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Owner implements Serializable {

	private static final long serialVersionUID = 3782729130611691854L;

	@Id
	@GeneratedValue
	private long id;
	// TODO Numéro de SECU ou Numéro carte séjour

	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	private String address;
	private String zipCode;
	private String town;
	private String phone;
	
	private long idOwnerWs;

	@OneToMany(fetch=FetchType.EAGER)
	private List<Resume> resumes;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
	public long getIdOwnerWs() {
		return idOwnerWs;
	}

	public void setIdOwnerWs(long idOwnerWs) {
		this.idOwnerWs = idOwnerWs;
	}

	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Owner)obj).getId();
	}
}