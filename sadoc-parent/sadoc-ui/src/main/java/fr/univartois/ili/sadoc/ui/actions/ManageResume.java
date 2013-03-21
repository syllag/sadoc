package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.Connection;

public class ManageResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 6796507791071667351L;
	
	private List<Resume> resumes = new ArrayList<Resume>();
	private Map<String, Object> session;
	
	@Override
	public String execute() {
		Owner owner = Connection.getUser(session);
		if (owner == null) {
			return "index";
		}
		resumes = owner.getResumes();
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
	public void setSession(Map<String, Object> session) {
		this.session = session;	
	}
}