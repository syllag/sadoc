package fr.univartois.ili.sadoc.sadocweb.spring;

import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;


public class SignDocumentRequest {
	
	private Byte[] doc;
	private String name;
	private Owner owner;
	private Competence[] competence;
	
	public SignDocumentRequest() {
	}

	public Byte[] getDoc() {
		return doc;
	}

	public void setDoc(Byte[] doc) {
		this.doc = doc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Competence[] getCompetence() {
		return competence;
	}

	public void setCompetence(Competence[] competence) {
		this.competence = competence;
	}
	
}
