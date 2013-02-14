package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.form.CreateResumeForm;

public class CreateResume extends ActionSupport implements SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreateResumeForm form;
	private Map<String, Object> session;

	/************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		session = ActionContext.getContext().getSession();
		
		//## TODO injection 
		IMetierUIServices metierUIServices = null ;
		
		if (form == null)
			return INPUT;
		
		int idOwner = (Integer) session.get("id");
		Resume resume = new Resume();
		Owner owner = new Owner();
		
		metierUIServices.findOwnerById(idOwner);
		
		//TODO recupérer de dao commun 
		Set<Competence> listCompetences = new HashSet<Competence>();
		String[] competences =  form.getListCompetences();
		
		assert(competences.length != 0);
		
		for(String competence : form.getListCompetences()){
			listCompetences.add(metierUIServices.findCompetenceById(Integer.parseInt(competence)));
		}
		List<Competence> listCompetencesTmp= new ArrayList<Competence>();
		for (Competence c : listCompetences){
			listCompetencesTmp.add(c);
		}
		resume.setCompetences(listCompetencesTmp);
		resume.setOwner(owner);

		owner.addResume(resume);
		try {
			metierUIServices.createResume(resume);			
			metierUIServices.updateOwner(owner);
		} catch (Exception e) {
			addActionMessage("Momentary problem... Please try again later.");
			return INPUT;
		}
		return SUCCESS;

	}


	public CreateResumeForm getForm() {
		return form;
	}


	public void setForm(CreateResumeForm resumeform) {
		this.form = resumeform;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
