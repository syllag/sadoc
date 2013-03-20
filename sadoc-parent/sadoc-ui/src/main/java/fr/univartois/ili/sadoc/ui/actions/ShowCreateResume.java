package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.Connection;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ShowCreateResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 6796507791071667351L;
	
	private List<Resume> resumes = new ArrayList<Resume>();
	private Map<String, Object> session;
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;

	
	public String execute() {
		Owner owner = Connection.getUser(session);
		if (owner == null) {
			return "index";
		}
		resumes = owner.getResumes();
		//resumes = new ArrayList<Resume>(resumes);
		if (resumes.size() != 0) {
			System.out.println("mon id : "+ owner.getId() +" taille : "+resumes.size());
			System.out.println(resumes);
			System.out.println(resumes.get(0).getId());
			System.out.println(resumes.get(0).getOwner().getFirstName());
		}
		return SUCCESS;
	}

	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> listResumes) {
		this.resumes = listResumes;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;		
	}
}
