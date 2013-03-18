package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Domaine implements Serializable, Comparable<Domaine> {

	private static final long serialVersionUID = -8971395079915618911L;

	private long id = -1L;
	private String codeDomaine = null;
	private String description = null;

	private Referentiel referentiel = null;
	private List<Competence> competences = new ArrayList<Competence>();

	public Domaine() {
	}

	public Domaine(String description) {
		this.setDescription(description);
	}

	public Domaine(String codeDomaine, String description) {
		this(description);
		this.codeDomaine = codeDomaine;
	}

	public Domaine(String codeDomaine, String description,
			Referentiel referentiel) {
		this(codeDomaine, description);
		this.referentiel = referentiel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeDomaine() {
		return codeDomaine;
	}

	public void setCodeDomaine(String codeDomaine) {
		this.codeDomaine = codeDomaine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Referentiel getReferentiel() {
		return referentiel;
	}

	public void setReferentiel(Referentiel referentiel) {
		this.referentiel = referentiel;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}
	
	public final boolean belongs(Referentiel referentiel) {
		return this.referentiel == referentiel;
	}

	@Override
	public int compareTo(Domaine o) {
		return codeDomaine.compareTo(o.getCodeDomaine());
	}
}
