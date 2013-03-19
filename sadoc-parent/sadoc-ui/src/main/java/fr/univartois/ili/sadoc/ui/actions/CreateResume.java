package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.form.CreateResumeForm;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;
import fr.univartois.ili.sadoc.ui.utils.ResumeUtil;

public class CreateResume extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CreateResumeForm form;
	private Map<String, Object> session;
	private Map<Referentiel, Map<Domaine, Map<Competence, List<Item>>>> arbreCompetences ;

	private IMetierCommunServices metierCommun = ContextFactory.getContext()
			.getBean(IMetierCommunServices.class);
	private IMetierUIServices metierUi = ContextFactory.getContext().getBean(
			IMetierUIServices.class);
	
	private Resume resume = null ;
	private Owner owner = null ;

	
	public String execute() {
	
		// TODO remove false when fake will be useless
		if (form == null) {
			// TODO to change when fake will be useless
			owner =  metierUi.findOwnerById(1);
			resume = Resume.getFake();
			resume.setOwner(owner);
			arbreCompetences = ResumeUtil.generateMap(resume);


			return INPUT;
		}
		
		List<Referentiel> refes = form.getChoiceReferentiels(metierCommun);
		List<Domaine> doms = form.getChoiceDomaines(metierCommun);
		List<Competence> comps = form.getChoiceCompetences(metierCommun);
		List<Item> items = form.getChoiceItems(metierCommun);


//		 long idOwner = (Long) session.get("id");
		 Owner owner = metierUi.findOwnerById(1);
		
		 Resume resume = new Resume();
		 resume.setOwner(owner);
		 resume.setReferentiels(refes);
		 resume.setDomaines(doms);
		 resume.setCompetences(comps);
		 resume.setItems(items);

		owner.addResume(resume);
		try {
			metierUi.createResume(resume);
			metierUi.updateOwner(owner);
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

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUi;
	}

	/**
	 * @return the arbreCompetences
	 */
	public Map<Referentiel, Map<Domaine, Map<Competence, List<Item>>>> getArbreCompetences() {
		return arbreCompetences;
	}

	/**
	 * @param arbreCompetences the arbreCompetences to set
	 */
	public void setArbreCompetences(
			Map<Referentiel, Map<Domaine, Map<Competence, List<Item>>>> arbreCompetences) {
		this.arbreCompetences = arbreCompetences;
	}



}
