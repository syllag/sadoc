package fr.univartois.ili.sadoc.ui.form;

public class ModifyUrlForm {
	
	private String url;
	private String documentId;
	
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

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
}
