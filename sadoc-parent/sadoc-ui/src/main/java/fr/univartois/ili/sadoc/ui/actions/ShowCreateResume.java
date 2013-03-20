package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.Connection;

public class ShowCreateResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 6796507791071667351L;
	
	private List<Resume> listResumes = new ArrayList<Resume>();
	private Map<String, Object> session;
	
	public String execute() {
		Owner owner = Connection.getUser(session);
		if (owner == null) {
			return "index";
		}
		listResumes = owner.getResumes();
		return SUCCESS;
	}

	public List<Resume> getListResumes() {
		return listResumes;
	}

	public void setListResumes(List<Resume> listResumes) {
		this.listResumes = listResumes;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;		
	}
}
