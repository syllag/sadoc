package fr.univartois.ili.sadoc.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

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
//		InitDataForTest idft = new InitDataForTest();
//		idft.createDataForTest();
//		int idOwner = (Integer) session.get("id");
//		OwnerDAO odao = new OwnerDAO();
//		AcquisitionDAO adao = new AcquisitionDAO();
//		Owner owner = odao.findById(idOwner);
//		for(Acquisition a : adao.findByOwner(owner)){
//			listCompetences.add(a.getCompetence());
//		}
		Competence competence1 = new Competence("competence 1 C2I1",
				"premier competence du C2I1");
		Competence competence2 = new Competence("competence 2 C2I1",
				"deuxiéme competence du C2I1");
		Competence competence3 = new Competence("competence 3 C2I1",
				"troisiéme competence du C2I1");
		
		competence1.setId(1);
		competence2.setId(2);
		competence3.setId(3);

		listCompetences.add(competence1);
		listCompetences.add(competence2);
		listCompetences.add(competence3);
		
		
		return SUCCESS;
	}

	public List<Competence> getListCompetences() {
		return listCompetences;
	}


}
