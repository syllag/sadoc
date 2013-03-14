package fr.univartois.ili.sadoc.dao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resume implements Serializable {

	private static final long serialVersionUID = -7146193425068939728L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Owner owner;

	@ElementCollection
	private List<String> referentiels = new ArrayList<String>();
	@ElementCollection
	private List<String> domaines = new ArrayList<String>();
	@ElementCollection
	private List<String> competences = new ArrayList<String>();
	@ElementCollection
	private List<String> items = new ArrayList<String>();
	
	public Resume() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<String> getReferentiels() {
		return referentiels;
	}

	public void setReferentiels(List<String> referentiels) {
		this.referentiels = referentiels;
	}

	public List<String> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<String> domaines) {
		this.domaines = domaines;
	}

	public List<String> getCompetences() {
		return competences;
	}

	public void setCompetences(List<String> competences) {
		this.competences = competences;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
}
