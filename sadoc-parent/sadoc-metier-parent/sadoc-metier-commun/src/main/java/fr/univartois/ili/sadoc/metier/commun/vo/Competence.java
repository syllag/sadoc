package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Competence implements Serializable {

	private static final long serialVersionUID = -3966140261748206775L;

	private long id = -1L;
	private String codeCompetence = null;
	private String description = null;

	private Domaine domaine = null;
	private List<Item> items = new ArrayList<Item>();

	public Competence() {
	}

	public Competence(String description) {
		this.description = description;
	}

	public Competence(String codeCompetence, String description) {
		this(description);
		this.codeCompetence = codeCompetence;
	}

	public Competence(String codeCompetence, String description, Domaine domaine) {
		this(codeCompetence, description);
		this.domaine = domaine;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeCompetence() {
		return codeCompetence;
	}

	public void setCodeCompetence(String codeCompetence) {
		this.codeCompetence = codeCompetence;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public final boolean belongs(Referentiel referentiel) {
		return domaine.belongs(referentiel);
	}
	
	public final boolean belongs(Domaine domaine) {
		return this.domaine == domaine;
	}
}