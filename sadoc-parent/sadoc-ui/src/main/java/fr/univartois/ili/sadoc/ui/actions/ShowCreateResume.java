package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.ui.ui.metier.ui.dao.OwnerDAO;

//TODO : Changer l'acquisition de la session
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
