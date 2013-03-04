package fr.univartois.ili.sadoc.ui.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.ui.form.ModifyUrlForm;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ModifyUrl extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private ModifyUrlForm form;
	private String id;
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;

	
	public String execute() {
		Document document = metierUIServices.findDocumentById(form.getDocumentId());
		
		if (document != null) {
			document.setUrl(form.getUrl());
			metierUIServices.updateDocument(document);
			id = document.getId();
			return SUCCESS;
		}
		return ERROR;
	}

	public ModifyUrlForm getForm() {
		return form;
	}

	public void setForm(ModifyUrlForm form) {
		this.form = form;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}
	
}
