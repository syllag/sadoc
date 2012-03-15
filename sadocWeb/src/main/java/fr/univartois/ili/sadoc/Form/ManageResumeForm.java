package fr.univartois.ili.sadoc.Form;


import java.util.Map;

public class ManageResumeForm {
	
	private String[] listCompetences;
	
	private Map<String, Object> session;
	
    /************************************************/
	
	public ManageResumeForm (String[] listCompetences) {
		this.listCompetences = listCompetences;
	}
	
	/************************************************/
	
	
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
