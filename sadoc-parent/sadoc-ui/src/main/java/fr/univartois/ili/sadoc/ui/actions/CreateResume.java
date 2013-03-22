package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.itextpdf.text.log.SysoLogger;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.form.CreateResumeForm;
import fr.univartois.ili.sadoc.ui.utils.Connection;
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
		owner = Connection.getUser(session);
		if (owner == null) {
			return "index";
		}
		
		if (form == null) {
			// TODO to change when fake will be useless
			resume = Resume.getFake();
			resume.setOwner(owner);
			arbreCompetences = ResumeUtil.generateMap(resume);
			return INPUT;
		}
		
		// Get the choices for the Resume from the form
		List<Referentiel> refes = form.getChoiceReferentiels(metierCommun);
		List<Domaine> doms = form.getChoiceDomaines(metierCommun);
		List<Competence> comps = form.getChoiceCompetences(metierCommun);
		List<Item> items = form.getChoiceItems(metierCommun);
		
		// Create the VO Resume
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return this.session;
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