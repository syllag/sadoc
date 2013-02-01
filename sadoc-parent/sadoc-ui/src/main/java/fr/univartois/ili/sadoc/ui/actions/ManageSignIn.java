package fr.univartois.ili.sadoc.ui.actions;

import java.security.MessageDigest;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageSignInForm;

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
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		session = ActionContext.getContext().getSession();
		if (session.get("mail")!=null) {
			return "astalavista";
		}
		
		if (form == null) {
			session.put("error", "");
			return INPUT;
		}
		
		//## TODO injection 
		IMetierUIServices metierUIServices = null ;
		
//##		OwnerDAO odao = new OwnerDAO();
//##		if (odao.findByMail(form.getMail()) != null) {
		if (metierUIServices.findOwnerByEmail(form.getMail()) != null){		
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
		personne.setId(personneWS.getId().intValue());
		personne.setMail(form.getMail());
		
		// TODO : cryptage password
		try {

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] p = messageDigest.digest(form.getPassword().getBytes());
			StringBuilder hashString = new StringBuilder();
			for (int i = 0; i < p.length; ++i) {
				String hex;
				hex = Integer.toHexString(p[i]);
				if (hex.length() == 1) {
					hashString.append('0');
					hashString.append(hex.charAt(hex.length() - 1));
				} else {
					hashString.append(hex.substring(hex.length() - 2));
				}
			}
			personne.setPassword(hashString.toString());
			
			//##odao.create(personne);
			metierUIServices.createOwner(personne);
			
			// TODO : connecter la personne
		} catch (Exception e) {
			e.printStackTrace();
			session.put("error", "Problème temporaire... Essayez plus tard.");
			return INPUT;
		}
		
		Synchronization s= new Synchronization();
		s.SynchronizeWebAPPDatabase(personne.getMail());
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
	public Map<String, Object> getSession(){
		return session;
	}
}
