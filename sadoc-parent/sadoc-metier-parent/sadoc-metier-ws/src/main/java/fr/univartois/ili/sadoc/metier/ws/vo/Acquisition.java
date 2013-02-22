package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Acquisition implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String id_item;
	private Date creationDate;
	
	private List<Document> documents;

	public Acquisition() {		
	}

	public Acquisition(long id, String id_item, Date creationDate,
			List<Document> documents) {
		super();
		this.id = id;
		this.id_item = id_item;
		this.creationDate = creationDate;
		this.documents = documents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getId_item() {
		return id_item;
	}

	public void setId_item(String id_item) {
		this.id_item = id_item;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	
	
	
	
}
