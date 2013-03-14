package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Domaine implements Serializable{

	private static final long serialVersionUID = 1351118578858097701L;

	@Id
	@GeneratedValue
	private long id;
	private String codeDomaine;
	private String description;
	
	@ManyToOne
	private Referentiel referentiel;
	@OneToMany(mappedBy="domaine")
	private List<Competence> competences;
	
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
	 * @return the codeDomaine
	 */
	public String getCodeDomaine() {
		return codeDomaine;
	}
	/**
	 * @param codeDomaine the codeDomaine to set
	 */
	public void setCodeDomaine(String codeDomaine) {
		this.codeDomaine = codeDomaine;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the referentiel
	 */
	public Referentiel getReferentiel() {
		return referentiel;
	}
	/**
	 * @param referentiel the referentiel to set
	 */
	public void setReferentiel(Referentiel referentiel) {
		this.referentiel = referentiel;
	}
	/**
	 * @return the competences
	 */
	public List<Competence> getCompetences() {
		return competences;
	}
	/**
	 * @param competences the competences to set
	 */
	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
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
		Domaine other = (Domaine) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
