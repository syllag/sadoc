package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import fr.univartois.ili.sadoc.entities.classes.Document;

public class ImportDocumentResponse {

	private List<Document> document;

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	
	
}
