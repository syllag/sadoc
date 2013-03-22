package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
             
public class GetDocumentInformationsResponse {

	private Owner owner;
	private List<Acquisition> acquisition;
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public List<Acquisition> getAcquisition() {
		return acquisition;
	}
	public void setAcquisition(List<Acquisition> acquisition) {
		this.acquisition = acquisition;
	}
	

	
	

}
