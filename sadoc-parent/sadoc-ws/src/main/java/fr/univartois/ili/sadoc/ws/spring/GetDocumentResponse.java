package fr.univartois.ili.sadoc.ws.spring;

import fr.univartois.ili.sadoc.metier.ws.vo.Document;

public class GetDocumentResponse {

	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
