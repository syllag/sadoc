package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OwnerWS implements Serializable{
	
	private static final long serialVersionUID = 68838356125074929L;
	
	@Id
	private long id;
	private String mail_initial;
	
	@OneToMany(mappedBy="ownerWs")
	List<Certificate> certificates;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the mail_initial
	 */
	public String getMail_initial() {
		return mail_initial;
	}
	/**
	 * @param mail_initial the mail_initial to set
	 */
	public void setMail_initial(String mail_initial) {
		this.mail_initial = mail_initial;
	}
	
}
