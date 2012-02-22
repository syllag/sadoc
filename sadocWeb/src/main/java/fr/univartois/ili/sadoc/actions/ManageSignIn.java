package fr.univartois.ili.sadoc.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageSignInForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Diane Dutartre <LiDaYuRan at gmail.com>
 * 
 */
public class ManageSignIn extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * formulaire contenant l'evenement qui va être creer
	 */
	private ManageSignInForm form;
	private Map<String, Object> session;
	
	/************************************************/
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		session = ActionContext.getContext().getSession();
		session.put("currentMenu", "SignIn");
		
		if (form == null) return INPUT;
		
		Owner personne = new Owner();
		personne.setFirstName(form.getFirstname());
		personne.setLastName(form.getName());
		personne.setMail(form.getMail());
		// enregistrement dans la base de donnée !
		// TODO : cryptage password
		try {
			personne.setPassword(form.getPassword());
			OwnerDAO.create(personne);
			// TODO : connecter la personne
		} catch (Exception e) {
			addActionMessage("Momentary problem... Please try agin later.");
			return INPUT;
		}
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		try {
			if (OwnerDAO.findByMail(form.getMail()) != null) {
				addActionMessage("A user already exist with this mail adress");
			}
		} catch (Exception e) {
			addActionMessage("Momentary problem... Please try agin later.");
		}
	}

	
	

	/************************************************/
	
	/**
	 * getter du formulaire de creation d'evenement
	 * 
	 * @return
	 */
	public ManageSignInForm getForm() {
		return form;
	}

	/**
	 * setter du formulaire d'evenement
	 * 
	 * @param creation
	 */
	public void setForm(ManageSignInForm signinform) {
		this.form = signinform;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

}
