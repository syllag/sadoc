package fr.univartois.ili.sadoc.metier.commun.vo;

import java.util.List;


/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Domaine {

	private long id;
	private String codeDomaine;
	private String description;

	private Referentiel referentiel;
	private List<Competence> competences;
	
	public Domaine() {
	}

	public Domaine(String description) {
		this.setDescription(description);
	}

	public Domaine(String codeDonaime, String description) {
		this.codeDomaine = codeDonaime;
		this.setDescription(description);
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

	
}
