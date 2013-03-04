package fr.univartois.ili.sadoc.ui.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ManageResume extends ActionSupport implements SessionAware,
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);
	public String execute() throws Exception {
		String mail = (String) session.get("mail");
		if (mail == null) {
			return "index";
		}
		Owner owner = metierUIServices.findOwnerByEmail(mail);
		request.setAttribute("resumes",owner.getResumes());
		return SUCCESS;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	private Map<String, Object> getSession() {
		return this.session;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
