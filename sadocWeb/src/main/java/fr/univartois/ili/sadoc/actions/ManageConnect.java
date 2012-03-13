package fr.univartois.ili.sadoc.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageConnectForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Damien Wattiez <Damien Wattiez at gmail.com>
 * 
 */
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
		// Create session
		session = ActionContext.getContext().getSession();
		
		if (connect == null) {
			session.put("incorrect", "Nok");
			return INPUT;
		}
		
		if (connect == null) {
			session.put("incorrect", "ok");
			return INPUT;
		}
		OwnerDAO odao = new OwnerDAO();
		Owner owner = odao.findOwner(connect.getEmail(), connect.getPassword());

		// if empty
		if (owner == null) {
			session.put("incorrect", "ok");
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
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
