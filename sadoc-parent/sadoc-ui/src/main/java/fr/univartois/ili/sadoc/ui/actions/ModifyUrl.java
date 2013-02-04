package fr.univartois.ili.sadoc.ui.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.ui.form.ModifyUrlForm;
import fr.univartois.ili.sadoc.ui.utils.TestID;

public class ModifyUrl extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModifyUrlForm form;
	private String id;
	
	public String execute() {
		//## TODO injection 
		IMetierUIServices metierUIServices = null ;

		//##DocumentDAO ddao = new DocumentDAO();
		//##Document document = ddao.findById(form.getDocumentId());
		Document document = metierUIServices.findDocumentById(form.getDocumentId());
		
		if (document != null) {
			document.setUrl(form.getUrl());
			//##ddao.update(document);
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
	
}
