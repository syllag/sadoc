package fr.univartois.ili.sadoc.metier.ui.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Competence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	private String description;
	private String acronym;
	
	private List<Degree> degrees= new ArrayList<Degree>();
	
	/************************************************/
	
	public Competence(){}
	
	public Competence(String name, String description){
		this.name=name;
		this.description=description;
	}
	
	/************************************************/
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public boolean equals(Competence competence) {
		return this.id == competence.getId();
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
}