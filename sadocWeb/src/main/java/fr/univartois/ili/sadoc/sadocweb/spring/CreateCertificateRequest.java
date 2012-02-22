package fr.univartois.ili.sadoc.sadocweb.spring;

import fr.univartois.ili.sadoc.entities.classes.Owner;


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
