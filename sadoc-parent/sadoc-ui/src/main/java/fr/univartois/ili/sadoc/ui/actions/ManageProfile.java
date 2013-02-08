package fr.univartois.ili.sadoc.ui.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageProfileForm;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class ManageProfile extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private ManageProfileForm form;
	private Map<String, Object> session;
	
	/************************************************/

	public String execute () {
		session = ActionContext.getContext().getSession();
		if (session.get("mail")==null) {
			return "astalavista";
		}
		if (form == null) {
			return INPUT;
		}
		
		//## TODO injection 
		IMetierUIServices metierUIServices = null ;
				
		String mail = (String) session.get("mail");
//##		OwnerDAO odao=new OwnerDAO();
//##		Owner owner = odao.findByMail(mail);
		Owner owner = metierUIServices.findOwnerByEmail(mail);
		
		//owner.setFirstName(form.getFirstName());
		//owner.setLastName(form.getLastName());
		owner.setAddress(form.getAdress());
		owner.setZipCode(form.getZipCode());
		owner.setTown(form.getTown());
		owner.setPhone(form.getPhone());
		if ( !(form.getPassword().isEmpty() 
				&& form.getPassword2().isEmpty()) 
				&& (form.getPassword().equals(form.getPassword2()))) {
					owner.setPassword(form.getPassword());
		}
		
		session.put("adress",owner.getAddress());
		session.put("zipCode", owner.getZipCode());
		session.put("town", owner.getTown());
		session.put("phone", owner.getPhone());
		ActionContext.getContext().setSession(session);
		
//##		odao.update(owner);
		metierUIServices.updateOwner(owner);
		
		
		return SUCCESS;
	}
	
	public void validate () {
		
		if ( (form != null) && !(form.getPassword().isEmpty() && form.getPassword2().isEmpty()) ) {
			if (form.getPassword().equals(form.getPassword2())) {
				addFieldError( "password2", "Les mots de passe sont différents.");
			}
		}
	}
	
	/************************************************/
	
	public ManageProfileForm getForm() {
		return form;
	}
	public void setForm(ManageProfileForm form) {
		this.form = form;
	}
	public void setSession(Map<String, Object> session){
		  session = this.getSession();
	}
	public Map<String, Object> getSession(){
		return session;
	}
}