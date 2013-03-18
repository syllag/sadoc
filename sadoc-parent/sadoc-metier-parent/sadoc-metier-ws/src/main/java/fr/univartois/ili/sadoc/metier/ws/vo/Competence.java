package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;


public class Competence implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String codeCompetence;
	private String description;	
	
	/************************************************/

	public Competence(){}
	
	public Competence(String codeCompetence, String description) {
		this.codeCompetence=codeCompetence;
		this.description=description;		
	}
	
	/************************************************/

	
	//
	// Pourquoi ne pas redéfinir la méthode equals ??
	// Au lieu de redéfinir compareTo
	//
	//
	// This class defines a compareTo(...) method but inherits its equals()
	// method from java.lang.Object. Generally, the value of compareTo should
	// return zero if and only if equals returns true. If this is violated,
	// weird and unpredictable failures will occur in classes such as
	// PriorityQueue. In Java 5 the PriorityQueue.remove method uses the
	// compareTo method, while in Java 6 it uses the equals method.
	
	public int compareTo(Object other) { 
	      String name1 = ((Competence) other).getCodeCompetence(); 
	      String name2 = this.getCodeCompetence()+this.getCodeCompetence(); 
	      return name2.compareTo(name1);
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
