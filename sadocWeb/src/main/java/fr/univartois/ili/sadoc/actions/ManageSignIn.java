package fr.univartois.ili.sadoc.actions;

import java.math.BigInteger;
import java.security.MessageDigest;

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

	public String execute() throws Exception {
		Owner personne = new Owner();
		personne.setFirstName(form.getFirsname());
		personne.setLastName(form.getName());
		personne.setMail(form.getMail());

		// enregistrement dans la base de donnée
		try {
			// crypte le password
			MessageDigest alg = MessageDigest.getInstance("MD5");
			String password = form.getPassword();
			alg.reset();
			alg.update(password.getBytes());
			byte[] msgDigest = alg.digest();
			BigInteger number = new BigInteger(1, msgDigest);
			String str = number.toString(16);
			
			personne.setPassword(str);
			OwnerDAO.create(personne);
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("Momentary problem... Please try agin later.");
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
			addActionMessage("The passwords aren't the same");
		}
		if (OwnerDAO.findByMail(form.getMail()) != null) {
			addActionMessage("A user already exist with this mail adress");
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
