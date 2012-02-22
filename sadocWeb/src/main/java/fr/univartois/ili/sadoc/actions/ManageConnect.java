package fr.univartois.ili.sadoc.actions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ManageConnectForm;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
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
	
	public String execute(){

		
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.mail = :mail AND o.password = :password ",
				Owner.class);
		query.setParameter("mail", connect.getEmail());
		query.setParameter("password", connect.getPassword());
		List<Owner> owner = query.getResultList();
		
		// if empty
		if(owner.size() == 0){
			return INPUT;
		}
		
		//take information profile
		

		return SUCCESS;
	}

	public ManageConnectForm getConnect() {
		return connect;
	}

	public void setConnect(ManageConnectForm connect) {
		this.connect = connect;
	}
	
	
	
}
