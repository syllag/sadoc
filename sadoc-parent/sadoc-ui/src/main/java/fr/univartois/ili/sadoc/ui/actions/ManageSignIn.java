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
import fr.univartois.ili.sadoc.ui.utils.PasswordUtil;

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
		if (session.get("mail") != null) {
			return "index";
		}

		if (form == null) {
			session.put("error", "");
			return INPUT;
		}

		if (metierUIServices.findOwnerByEmail(form.getMail()) != null) {
			session.put("error", "Mail déjà utilisé");
			return INPUT;
		}

		ClientWebServiceImpl webService = new ClientWebServiceImpl();
		fr.univartois.ili.sadoc.client.webservice.tools.Owner personneWS = webService
				.getOwner(form.getMail());
		if (personneWS == null) {
			session.put("error", "Vous n'avez pas encore exporté de documents.");
			return INPUT;
		}

		Owner personne = new Owner();
		personne.setFirstName(personneWS.getFirstName());
		personne.setLastName(personneWS.getLastName());
		personne.setId(personneWS.getId());
		personne.setMail(form.getMail());
		try {
			personne.setPassword(PasswordUtil.encrypt(form.getPassword())
					.toString());
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
