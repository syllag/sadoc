package fr.univartois.ili.sadoc.ws.spring;

import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;


public class SignDocumentRequest {
	
	private byte[] doc;
	private String name;
	private Owner owner;
	private Acquisition[] acquisition;
	
	public SignDocumentRequest() {
	}

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
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

	/**
	 * @return the acquisition
	 */
	public Acquisition[] getAcquisition() {
		return acquisition;
	}

	/**
	 * @param acquisition the acquisition to set
	 */
	public void setAcquisition(Acquisition[] acquisition) {
		this.acquisition = acquisition;
	}

	
	
}
