package fr.univartois.ili.sadoc.metier.ui.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Competence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String codeCompetence;
	private String description;	
	
	private List<Degree> degrees= new ArrayList<Degree>();
	
	/************************************************/
	
	public Competence(){}
	
	public Competence(String codeCompetence, String description){
		this.codeCompetence=codeCompetence;
		this.description=description;
	}
	
	/************************************************/
	
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

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public boolean equals(Competence competence) {
		return this.id == competence.getId();
	}

	/**
	 * @return the codeCompetence
	 */
	public String getCodeCompetence() {
		return codeCompetence;
	}

	/**
	 * @param codeCompetence the codeCompetence to set
	 */
	public void setCodeCompetence(String codeCompetence) {
		this.codeCompetence = codeCompetence;
	}

	
	
	
}