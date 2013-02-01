package fr.univartois.ili.sadoc.ws.spring;

import fr.univartois.ili.sadoc.metier.ws.vo.Owner;;


public class CreateCertificateRequest {
	
	private Owner owner;
	
	public CreateCertificateRequest() {
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
