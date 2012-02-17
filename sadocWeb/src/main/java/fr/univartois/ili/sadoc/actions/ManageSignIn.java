package fr.univartois.ili.sadoc.actions;

public class ManageSignIn {

	/**
	 * formulaire contenant l'evenement qui va être creer
	 */
	private ManageSignInForm form;

	public String execute() throws Exception {
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