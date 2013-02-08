package fr.univartois.ili.sadoc.metier.commun.vo;

import java.io.Serializable;

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

	private int id;
	private String codeCompetence;
	private String description;

	public Competence() {
	}

	public Competence(String description) {
		this.setDescription(description);
	}

	public Competence(String codeCompetence, String description) {
		this.setCodeCompetence(codeCompetence);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}