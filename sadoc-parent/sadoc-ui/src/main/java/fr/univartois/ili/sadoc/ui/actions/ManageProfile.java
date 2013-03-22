package fr.univartois.ili.sadoc.ui.actions;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageProfileForm;
import fr.univartois.ili.sadoc.ui.utils.Connection;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;
import fr.univartois.ili.sadoc.ui.utils.Form;

public class ManageProfile extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private ManageProfileForm form;
	private Map<String, Object> session;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	@Override
	public String execute() throws NoSuchAlgorithmException {
		Owner owner = Connection.getUser(session);
		if (owner == null) {
			return "index";
		}
		
		if (form == null) {
			return INPUT;
		}

		// Add information of user account from the form
		owner.setAddress(Form.normalizeAddress(form.getAddress()));
		owner.setZipCode(form.getZipCode());
		owner.setTown(Form.normalizeTown(form.getTown()));
		owner.setPhone(form.getPhone());
		if (!form.getPassword().isEmpty()) {
			owner.setPassword(Connection.encryptPassword(form.getPassword()));
		}
		metierUIServices.updateOwner(owner);

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (form != null) {
			if (!(form.getPassword().isEmpty() && form.getPassword2()
						.isEmpty())) {
				if (!form.getPassword().equals(form.getPassword2())) {
					addFieldError("form.password2", "Les mots de passe sont différents.");
				}
				
				if (form.getPassword().length() < 8) {
					addFieldError("form.password2", "Le mot de passe doit comporter au moins 8 caractères");
				}
			
			}
			
			if (!(form.getZipCode().isEmpty())) {
				if (!Form.isCorrectZipCode(form.getZipCode())){
					addFieldError("form.zipCode", "Le code postal n'est pas au bon format");
				}
			}
			
			if (!(form.getPhone().isEmpty())) {
				if (!Form.isCorrectPhoneNumber(form.getPhone())){
					addFieldError("form.phone", "Le numéro de téléphone n'est pas au bon format");
				}
			}
		}
	}

	public ManageProfileForm getForm() {
		return form;
	}

	public void setForm(ManageProfileForm form) {
		this.form = form;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
