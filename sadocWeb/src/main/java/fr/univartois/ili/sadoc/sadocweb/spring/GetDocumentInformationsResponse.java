package fr.univartois.ili.sadoc.sadocweb.spring;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Owner;
             
public class GetDocumentInformationsResponse {

	private Owner owner;
	private Competence[] competences;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Competence[] getCompetences() {
		return competences;
	}

	public void setCompetences(Competence[] competences) {
		this.competences = competences;
	}

}
