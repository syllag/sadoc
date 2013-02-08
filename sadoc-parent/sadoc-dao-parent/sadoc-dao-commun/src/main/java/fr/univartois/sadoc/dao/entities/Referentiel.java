package fr.univartois.sadoc.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Referentiel implements Serializable {

	private static final long serialVersionUID = -1754735558436958586L;

	@Id
	private long id;
	private String codeReferentiel;
	private String name;
	private String description;
	private long seuil;
	private String url;
	
	@OneToMany(mappedBy="referentiel")
	private List<Domaine> domaines ;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the codeReferentiel
	 */
	public String getCodeReferentiel() {
		return codeReferentiel;
	}

	/**
	 * @param codeReferentiel
	 *            the codeReferentiel to set
	 */
	public void setCodeReferentiel(String codeReferentiel) {
		this.codeReferentiel = codeReferentiel;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the seuil
	 */
	public long getSeuil() {
		return seuil;
	}

	/**
	 * @param seuil
	 *            the seuil to set
	 */
	public void setSeuil(long seuil) {
		this.seuil = seuil;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the domaines
	 */
	public List<Domaine> getDomaines() {
		return domaines;
	}

	/**
	 * @param domaines the domaines to set
	 */
	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}

}
