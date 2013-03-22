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
	private List<Long> referentiels = new ArrayList<Long>();
	@ElementCollection
	private List<Long> domaines = new ArrayList<Long>();
	@ElementCollection
	private List<Long> competences = new ArrayList<Long>();
	@ElementCollection
	private List<Long> items = new ArrayList<Long>();
	
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

	public List<Long> getReferentiels() {
		return referentiels;
	}

	public void setReferentiels(List<Long> referentiels) {
		this.referentiels = referentiels;
	}

	public List<Long> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Long> domaines) {
		this.domaines = domaines;
	}

	public List<Long> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Long> competences) {
		this.competences = competences;
	}

	public List<Long> getItems() {
		return items;
	}

	public void setItems(List<Long> items) {
		this.items = items;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != getClass()) {
			return false;
		}
		Resume resume = (Resume) o;
		return resume.getId() == id;
	}
}
