package fr.univartois.ili.sadoc.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class DownloadP7S extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sa = null;
	private InputStream fileInputStream;
	private Map<String, Object> session;
	
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;


	public String getSa() {
		return sa;
	}

	public void setSa(String sa) {
		this.sa = sa;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String execute() throws Exception {
		session = ActionContext.getContext().getSession();
		if (session.get("mail")==null) {
			return "astalavista";
		}
				
		//long realID = TestID.findRealID(sa);
		
		Document doc = metierUIServices.findDocumentById(sa);
		
		if (doc == null) {
			return ERROR;
		}
		FileOutputStream envfos = new FileOutputStream("/tmp/authenticate.p7s");
		envfos.write(doc.getP7s());
		envfos.close();
	    fileInputStream = new FileInputStream(new File("/tmp/authenticate.p7s"));
		return SUCCESS;
	}
	
	public void setSession(Map<String, Object> session){
		  session = this.getSession();
	}
	public Map<String, Object> getSession(){
		return session;
	}
	
	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}
}
