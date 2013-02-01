package fr.univartois.ili.sadoc.metier.ui.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Degree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	private String description;
		
	private List<Competence> competences = new ArrayList<Competence>();
	
	/************************************************/

	public Degree(){}
	
	public Degree(String name,String description){
		this.name=name;
		this.description=description;
	}
	
	/************************************************/

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}