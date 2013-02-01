package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.form.CreateResumeForm;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.dao.OwnerDAO;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.dao.ResumeDAO;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.entities.Competence;


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
		
		Set<Competence> listCompetences = new HashSet<Competence>();
		String[] competences =  form.getListCompetences();
		
		assert(competences.length != 0);
		
		for(String competence : form.getListCompetences())
			listCompetences.add(cdao.findById(Integer.parseInt(competence)));
		
		List<Competence> listCompetencesTmp= new ArrayList<Competence>();
		for (Competence c : listCompetences){
			listCompetencesTmp.add(c);
		}
		resume.setCompetences(listCompetencesTmp);
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
