package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 4976187438361538939L;

	private long id = 0L;
	private String codeItem = null;
	private String description = null;
	private long poids = 0L;
	private long empreinte = 0L;
	private String type = null;

	private Competence competence = null;

	public Item() {
	}

	public Item(String description) {
		this.description = description;
	}

	public Item(String codeItem, String description) {
		this(description);
		this.codeItem = codeItem;
	}

	public Item(String codeItem, String description, Competence competence) {
		this(description, codeItem);
		this.competence = competence;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeItem() {
		return codeItem;
	}

	public void setCodeItem(String codeItem) {
		this.codeItem = codeItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPoids() {
		return poids;
	}

	public void setPoids(long poids) {
		this.poids = poids;
	}

	public long getEmpreinte() {
		return empreinte;
	}

	public void setEmpreinte(long empreinte) {
		this.empreinte = empreinte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Competence getCompetence() {
		return competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}
	
	public final boolean belongs(Referentiel referentiel) {
		return competence.belongs(referentiel);
	}
	
	public final boolean belongs(Domaine domaine) {
		return competence.belongs(domaine);
	}
	
	public final boolean belongs(Competence competence) {
		return this.competence == competence; 
	}
}
