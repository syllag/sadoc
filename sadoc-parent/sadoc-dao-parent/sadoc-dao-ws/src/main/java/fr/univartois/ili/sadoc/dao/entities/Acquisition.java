package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Acquisition implements Serializable {

	
	private static final long serialVersionUID = 3216317303555902356L;

	@Id
	@GeneratedValue
	private long id;
	
	private String id_item;
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	//rel
	@ManyToMany
	private List<Document> documents;

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
	 * @return the id_item
	 */
	public String getId_item() {
		return id_item;
	}

	/**
	 * @param id_item the id_item to set
	 */
	public void setId_item(String id_item) {
		this.id_item = id_item;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the documents
	 */
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
}
