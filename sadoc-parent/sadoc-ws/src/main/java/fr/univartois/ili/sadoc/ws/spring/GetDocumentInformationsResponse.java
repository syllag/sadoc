package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
             
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
