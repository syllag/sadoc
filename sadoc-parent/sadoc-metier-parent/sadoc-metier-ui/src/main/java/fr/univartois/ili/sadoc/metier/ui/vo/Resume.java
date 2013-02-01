package fr.univartois.ili.sadoc.metier.ui.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Resume implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private Owner owner;
	
	private List<Competence> competences;

	/************************************************/
	
	public Resume(){}
	
	public Resume(Owner owner,List<Competence> competences){
		this.owner=owner;
		this.competences=competences;
	}
	
	/************************************************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

}
