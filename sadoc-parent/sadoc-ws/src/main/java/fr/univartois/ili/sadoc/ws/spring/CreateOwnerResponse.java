package fr.univartois.ili.sadoc.ws.spring;

import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

public class CreateOwnerResponse {

	private Owner owner;

	public CreateOwnerResponse() {
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
