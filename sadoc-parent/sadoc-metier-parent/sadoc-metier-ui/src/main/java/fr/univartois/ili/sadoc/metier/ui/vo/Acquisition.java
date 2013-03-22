package fr.univartois.ili.sadoc.metier.ui.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * @autor damien wattiez <damien.wattiez at gmail.com>
 */
public class Acquisition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String id_item;

	private Date creationDate;

	/************************************************/

	public Acquisition() {
	}

	public Acquisition(long id, String id_item, Date creationDate) {
		super();
		this.id = id;
		this.id_item = id_item;
		this.creationDate = creationDate;
	}

	/************************************************/

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

}
