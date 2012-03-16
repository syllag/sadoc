package fr.univartois.ili.sadoc.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.ResumeDAO;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Resume;

public class ManageResume  extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	
	
	public String execute() throws Exception {
		session = ActionContext.getContext().getSession();
		if (session.get("mail")==null) {
			return "astalavista";
		}
		
		
		ResumeDAO rdao=new ResumeDAO();
		Resume res=new Resume();
		
		String mail = (String) session.get("mail");
		OwnerDAO odao=new OwnerDAO();
		Owner own=odao.findByMail(mail);
		//res.setOwner(own);		
		//rdao.create(res);
		List<Resume> resums=(List<Resume>)session.get("listResume");
		for(Resume r:resums){
			System.out.println("CV"+r.getId());
		}
		ActionContext.getContext().setSession(session);
		
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> arg0) {
		session = this.getSession();
		
	}


	private Map<String, Object> getSession() {
		
		return session;
	}

}
