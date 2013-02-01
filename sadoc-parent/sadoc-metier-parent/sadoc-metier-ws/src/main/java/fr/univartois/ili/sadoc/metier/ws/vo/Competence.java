package fr.univartois.ili.sadoc.metier.ws.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 *
 */
public class Competence implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	private String description;
	private String acronym;
	
	private Date creationDate;
	
	/************************************************/

	public Competence(){}
	
	public Competence(String name, String description) {
		this.name=name;
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
	      String name1 = ((Competence) other).getName(); 
	      String name2 = this.getName()+this.getName(); 
	      return name2.compareTo(name1);
	   } 
	
	/************************************************/
	
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

	public Date getCreationDate() {
		return (Date) creationDate.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = (Date) creationDate.clone();
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

}
