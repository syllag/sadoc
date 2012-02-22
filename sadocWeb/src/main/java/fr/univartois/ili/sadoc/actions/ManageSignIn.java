package fr.univartois.ili.sadoc.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.univartois.ili.sadoc.Form.ManageSignInForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

public class ManageSignIn extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * formulaire contenant l'evenement qui va être creer
	 */
	private ManageSignInForm form;

	public String execute() {
		Owner personne = new Owner();
		personne.setFirstName(form.getFirstname());
		personne.setLastName(form.getName());
		personne.setMail(form.getMail());
		// enregistrement dans la base de donnée !
		// TODO : cryptage password
		try {
			personne.setPassword(form.getPassword());
			OwnerDAO.create(personne);
		} catch (Exception e) {
			addActionMessage("Momentary problem... Please try agin later.");
			return "input";
		}
		return "success";
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

}
