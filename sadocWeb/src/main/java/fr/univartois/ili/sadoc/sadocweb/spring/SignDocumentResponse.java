package fr.univartois.ili.sadoc.sadocweb.spring;


public class SignDocumentResponse {

	private byte[] doc;

	public SignDocumentResponse() {
	}

	public byte[] getDoc() {
		return doc.clone();
	}

	public void setDoc(byte[] doc) {
		this.doc = doc.clone();
	}


}
