package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Competence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5559364452348508266L;

	private long id;
	private String codeCompetence;
	private String description;
	
	private Domaine domaine;
	private List<Item> items;

	public Competence() {
	}

	public Competence(String description) {
		this.setDescription(description);
	}

	public Competence(String codeCompetence, String description) {
		this.setCodeCompetence(codeCompetence);
		this.setDescription(description);
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

	
}