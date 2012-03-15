package fr.univartois.ili.sadoc.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageResumeForm;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.ResumeDAO;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Resume;


public class ManageResume extends ActionSupport implements SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManageResumeForm form;
	private Map<String, Object> session;
	private ResumeDAO rdao;
	/************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){

		if (form == null)
			return INPUT;
		rdao = new ResumeDAO();
		
		Resume resume = new Resume();
		CompetenceDAO cdao = new CompetenceDAO();
		ResumeDAO rdao = new ResumeDAO();
		List<Competence> listCompetences = new ArrayList<Competence>();
		for(String competence : form.getListCompetences()){
			cdao.findById(Integer.parseInt(competence));
		}
		resume.setCompetences(listCompetences);
		
		try {
			rdao.create(resume);
		} catch (Exception e) {
			addActionMessage("Momentary problem... Please try again later.");
			return INPUT;
		}
		return SUCCESS;

	}


	public ManageResumeForm getForm() {
		return form;
	}


	public void setForm(ManageResumeForm signinform) {
		this.form = signinform;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
