package fr.univartois.ili.sadoc.ui.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageProfileForm;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ManageProfile extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private ManageProfileForm form;
	private Map<String, Object> session;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	public String execute() {
		if (session.get("mail") == null) {
			/**
			 * non logged user
			 */
			return "index";
		}
		if (form == null) {
			return INPUT;
		}

		String mail = (String) session.get("mail");
		Owner owner = metierUIServices.findOwnerByEmail(mail);
		/**
		 * Add information of user account from the form
		 */
		owner.setAddress(form.getAddress());
		owner.setZipCode(form.getZipCode());
		owner.setTown(form.getTown());
		owner.setPhone(form.getPhone());
		if (!(form.getPassword().isEmpty() && form.getPassword2().isEmpty())
				&& form.getPassword().equals(form.getPassword2())) {
			owner.setPassword(form.getPassword());
		}

		session.put("owner", owner);
		/**
		 * Add modification
		 */
		metierUIServices.updateOwner(owner);

		return SUCCESS;
	}

	public void validate() {

		if ((form != null)
				&& !(form.getPassword().isEmpty() && form.getPassword2()
						.isEmpty())) {
			if (form.getPassword().equals(form.getPassword2())) {
				addFieldError("password2", "Les mots de passe sont différents.");
			}
		}
		/**
		 * FIXME : add content checking by xml validation for other field
		 */
	}

	public ManageProfileForm getForm() {
		return form;
	}

	public void setForm(ManageProfileForm form) {
		this.form = form;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
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
