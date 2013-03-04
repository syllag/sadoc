package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ShowCreateResume extends ActionSupport implements SessionAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Competence> listCompetences = new ArrayList<Competence>();
	private Map<String, Object> session;
	private HttpServletRequest request;
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;
	
	public String execute() {
		long idOwner = (Integer) session.get("id");
		Owner owner = metierUIServices.findOwnerById(idOwner);
		for (Acquisition acquisition : metierUIServices.findAcquisitionByOwner(owner)){
			listCompetences.add(acquisition.getCompetence());
		}
		request.setAttribute("listCompetences", listCompetences);
		return SUCCESS;
	}

	public List<Competence> getListCompetences() {
		return listCompetences;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;		
	}
}
