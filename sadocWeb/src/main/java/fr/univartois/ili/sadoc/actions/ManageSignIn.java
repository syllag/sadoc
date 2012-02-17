package fr.univartois.ili.sadoc.actions;

import fr.univartois.ili.sadoc.Form.ManageSignInForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

public class ManageSignIn {

	/**
	 * formulaire contenant l'evenement qui va être creer
	 */
	private ManageSignInForm form;

	public String execute() throws Exception {

		Owner personne = new Owner();
		personne.setFirstName(form.getFirsname());
		personne.setLastName(form.getName());
		personne.setMail(form.getMail());
		personne.setPassword(form.getPassword());
  
		// enregistrement dans la base de donnée
		try {
	    	OwnerDAO.create(personne);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		// validation du mot de passe
		if (!form.getPassword().equals(form.getPassword2())) {

		}
		if (form.getFirsname().length() == 0 || form.getName().length() == 0
				|| form.getMail().length() == 0 || form.getPassword().length() == 0){
			
		}
		
    	if (OwnerDAO.findByMail(form.getMail()) != null){
    		
    		
    	}
			
		// addFieldError("creation.description",getText("error.creation.description"));

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