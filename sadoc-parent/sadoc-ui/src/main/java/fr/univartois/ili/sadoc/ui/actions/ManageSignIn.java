package fr.univartois.ili.sadoc.ui.actions;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageSignInForm;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;
import fr.univartois.ili.sadoc.ui.utils.Form;
import fr.univartois.ili.sadoc.ui.utils.Connection;

public class ManageSignIn extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	/**
	 * formulaire contenant l'evenement qui va être creer
	 */
	private ManageSignInForm form;
	private Map<String, Object> session;
	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	public String execute() {
		if (Connection.getUser(session) != null) {
			return "index";
		}

		if (form == null) {
			return INPUT;
		}

		if (metierUIServices.findOwnerByEmail(form.getMail()) != null) {
			
			session.put("error", "Mail déjà utilisé");
			return INPUT;
		}

		ClientWebServiceImpl webService = new ClientWebServiceImpl();
		fr.univartois.ili.sadoc.client.webservice.tools.Owner personneWS = webService
				.getOwner(form.getMail());

		// TODO Fake user
		personneWS = new fr.univartois.ili.sadoc.client.webservice.tools.Owner();
		personneWS.setId(1);		

		// TODO remove "false" condition when fake user will be removed
		if (false && personneWS == null) {
			session.put("error", "Vous n'avez pas encore exporté de documents.");
			return INPUT;
		}

		Owner personne = new Owner();
		personne.setFirstName(form.getFirstName());
		personne.setLastName(form.getLastName());
		personne.setId(personneWS.getId());
		personne.setMail(form.getMail());
		try {
			personne.setPassword(Connection.encryptPassword(form.getPassword()));
			metierUIServices.createOwner(personne);
			/**
			 * TODO : connecter la personne
			 */
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			session.put("error", "Problème temporaire... Essayez plus tard.");
			return INPUT;
		}

		/**
		 * Synchronization s= new Synchronization();
		 * s.SynchronizeWebAPPDatabase(personne.getMail());
		 */
		return SUCCESS;
	}

	public void validate() {
		if (form != null) {
			if (form.getPassword().isEmpty()) {
				addFieldError("form.password", "Le mot de passe est requis");
			} else if (form.getPassword().length() < 8) {
				addFieldError("form.password",
						"Le mot de passe doit faire au minimum 8 caractères.");
			} else if (!(form.getPassword().equals(form.getPassword2()))) {
				addFieldError("form.password2",
						"Les mots de passe sont différents.");
			}

			if (form.getFirstName().isEmpty()) {
				addFieldError("form.firstName", "Le prénom est requis");
			}
			if (form.getLastName().isEmpty()) {
				addFieldError("form.lastName", "Le nom est requis");
			}
			if (form.getMail().isEmpty()) {
				addFieldError("form.mail", "L'adresse mail est requise");
			} else if (!Form.isValidEmailAddress(form.getMail())) {
				addFieldError("form.mail",
						"L'adresse email n'est pas dans un format valide");
			} else if (metierUIServices.findOwnerByEmail(form.getMail()) != null){
				addFieldError("form.mail", "L'adresse mail est déjà utilisée");
			}
			
		}
	}

	/**
	 * setter du formulaire d'evenement
	 * 
	 * @param creation
	 */
	public void setForm(ManageSignInForm signinform) {
		this.form = signinform;
	}
	
	/**
	 * getter du formulaire de creation d'evenement
	 * 
	 * @return
	 */
	public ManageSignInForm getForm() {
		return form;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}
}
