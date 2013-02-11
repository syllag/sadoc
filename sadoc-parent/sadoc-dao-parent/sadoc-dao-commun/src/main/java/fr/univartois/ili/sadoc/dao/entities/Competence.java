package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Competence implements Serializable {

	private static final long serialVersionUID = -8507546730554845454L;

	@Id
	private long id;
	private String codeCompetence;
	private String description;

	@ManyToOne
	private Domaine domaine;
	@OneToMany(mappedBy = "competence")
	private List<Item> items;

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
	 * @return the codeCompetence
	 */
	public String getCodeCompetence() {
		return codeCompetence;
	}

	/**
	 * @param codeCompetence
	 *            the codeCompetence to set
	 */
	public void setCodeCompetence(String codeCompetence) {
		this.codeCompetence = codeCompetence;
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
	 * @return the domaine
	 */
	public Domaine getDomaine() {
		return domaine;
	}

	/**
	 * @param domaine the domaine to set
	 */
	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
