package fr.univartois.ili.sadoc.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;


public class ShowCreateResume extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Competence> listCompetences = new ArrayList<Competence>();
	private Map<String, Object> session;
	
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String execute() {
		session = ActionContext.getContext().getSession();
		

		int idOwner = (Integer) session.get("id");
		OwnerDAO odao = new OwnerDAO();
		AcquisitionDAO adao = new AcquisitionDAO();
		Owner owner = odao.findById(idOwner);
		for(Acquisition a : adao.findByOwner(owner)){
			listCompetences.add(a.getCompetence());
		}
		return SUCCESS;
	}

	public List<Competence> getListCompetences() {
		return listCompetences;
	}


}
