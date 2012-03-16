package fr.univartois.ili.sadoc.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.CreateResumeForm;
import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.ResumeDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Resume;


public class CreateResume extends ActionSupport implements SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreateResumeForm form;
	private Map<String, Object> session;
	private ResumeDAO rdao;
	/************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		session = ActionContext.getContext().getSession();
		
		if (form == null)
			return INPUT;
		
		rdao = new ResumeDAO();
		int idOwner = (Integer) session.get("id");
		Resume resume = new Resume();
		Owner owner = new Owner();
		OwnerDAO odao = new OwnerDAO();
		CompetenceDAO cdao = new CompetenceDAO();
		ResumeDAO rdao = new ResumeDAO();
		owner = odao.findById(idOwner); 
		
		List<Competence> listCompetences = new ArrayList<Competence>();
		String[] competences =  form.getListCompetences();
		
		assert(competences.length != 0);
		
		for(String competence : form.getListCompetences())
			listCompetences.add(cdao.findById(Integer.parseInt(competence)));
		

		
		resume.setCompetences(listCompetences);
		resume.setOwner(owner);

		owner.addResume(resume);
		try {
			rdao.create(resume);
			odao.update(owner);
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
