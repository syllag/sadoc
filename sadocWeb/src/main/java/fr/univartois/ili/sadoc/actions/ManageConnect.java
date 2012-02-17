package fr.univartois.ili.sadoc.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageConnectForm;

public class ManageConnect extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * forn contains connect
	 */
	private ManageConnectForm connect;
	
	public String execute(){
		System.out.println(connect.getEmail());
		System.out.println(connect.getPassword());
		return SUCCESS;
	}
	
}
