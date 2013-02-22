package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ManageResume  extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;

	
	public String execute() throws Exception {
		session = ActionContext.getContext().getSession();
		if (session.get("mail")==null) {
			return "astalavista";
		}
		
		Resume res=new Resume();
		
		String mail = (String) session.get("mail");

		Owner own = metierUIServices.findOwnerByEmail(mail);
		
		//res.setOwner(own);		
		//rdao.create(res);
		List<Resume> resums=(List<Resume>)session.get("listResume");

		ActionContext.getContext().setSession(session);
		
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> arg0) {
		session = this.getSession();
		
	}


	private Map<String, Object> getSession() {
		
		return session;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

}
