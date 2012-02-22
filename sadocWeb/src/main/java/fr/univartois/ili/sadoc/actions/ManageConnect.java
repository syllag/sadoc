package fr.univartois.ili.sadoc.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageConnectForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

public class ManageConnect extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * form contains connect
	 */
	private ManageConnectForm connect;
	private Map<String, Object> session;
	
	public String execute() {
		session = ActionContext.getContext().getSession();
		session.put("currentMenu", "ValidateConnect");
		
		if (connect == null) return INPUT;
		Owner owner = OwnerDAO.findOwner(connect.getEmail(),
				connect.getPassword());

		// if empty
		if (owner == null) {
			return INPUT;
		}

		// take information profile

		return SUCCESS;
	}

	public ManageConnectForm getConnect() {
		return connect;
	}

	public void setConnect(ManageConnectForm connect) {
		this.connect = connect;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

}
