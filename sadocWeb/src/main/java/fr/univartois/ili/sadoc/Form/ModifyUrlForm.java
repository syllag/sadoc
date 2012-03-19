package fr.univartois.ili.sadoc.Form;

import fr.univartois.ili.sadoc.entities.Document;

public class ModifyUrlForm {
	
	private String url;
	private int documentId;
	
	public ModifyUrlForm() {
	}
	
	public ModifyUrlForm (String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	
}
