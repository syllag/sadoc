package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import fr.univartois.ili.sadoc.entitiesws.classes.Competence;
import fr.univartois.ili.sadoc.entitiesws.classes.Owner;
             
public class GetDocumentInformationsResponse {

	private Owner owner;
	private List<Competence> competence;
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public List<Competence> getCompetence() {
		return competence;
	}
	public void setCompetence(List<Competence> competence) {
		this.competence = competence;
	}
	

	
	

}
