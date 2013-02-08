package fr.univartois.ili.sadoc.ui.form;


import java.util.Map;

public class CreateResumeForm {
	
	private String[] listCompetences;
	
	private Map<String, Object> session;
	
	public CreateResumeForm() {
	}
	
	public CreateResumeForm (String[] listCompetences) {
		super();
		this.listCompetences = listCompetences;
	}
	
	
	public String[] getListCompetences() {
		return listCompetences;
	}
	
	public void setListCompetences(String[] listCompetences) {
		this.listCompetences = listCompetences;

	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}