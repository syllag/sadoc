package fr.univartois.ili.sadoc.ui.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.Connection;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class RemoveResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private long idresume; 
	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);
	
	
	public long getId() {
		return idresume;
	}

	public void setId(long idresume) {
		this.idresume = idresume;
	}

	public String execute() {
		Owner owner = Connection.getUser(session);
		if (owner == null){
			return "index";
		}
		
		Resume resume = metierUIServices.findResumeById(idresume);
		if(resume.getOwner().getId() != owner.getId()) {
			throw new IllegalStateException();
		}
		
		owner.getResumes().remove(resume);
		metierUIServices.updateOwner(owner);
		metierUIServices.removeResume(resume);
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
}