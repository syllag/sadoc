package fr.univartois.ili.sadoc.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageConnectForm;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Owner;

public class ManageConnect extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * form contains connect
	 */
	private ManageConnectForm connect;

	public String execute() {
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

}
