package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 8473129775868773277L;
	
	@Id
	private long id;
	private String codeItem;
	private String description;
	private long poids;
	private long empreinte;
	private String type;
	
	@ManyToOne
	private Competence competence;
	

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the codeItem
	 */
	public String getCodeItem() {
		return codeItem;
	}

	/**
	 * @param codeItem
	 *            the codeItem to set
	 */
	public void setCodeItem(String codeItem) {
		this.codeItem = codeItem;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the poids
	 */
	public long getPoids() {
		return poids;
	}

	/**
	 * @param poids
	 *            the poids to set
	 */
	public void setPoids(long poids) {
		this.poids = poids;
	}

	/**
	 * @return the empreinte
	 */
	public long getEmpreinte() {
		return empreinte;
	}

	/**
	 * @param empreinte
	 *            the empreinte to set
	 */
	public void setEmpreinte(long empreinte) {
		this.empreinte = empreinte;
	}

	/**
	 * @return the competence
	 */
	public Competence getCompetence() {
		return competence;
	}

	/**
	 * @param competence the competence to set
	 */
	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
