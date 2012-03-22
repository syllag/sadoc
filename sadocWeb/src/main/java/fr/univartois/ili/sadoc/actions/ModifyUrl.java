package fr.univartois.ili.sadoc.actions;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ModifyUrlForm;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.utils.TestID;

public class ModifyUrl extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModifyUrlForm form;
	private String id;
	
	public String execute() {
		DocumentDAO ddao = new DocumentDAO();
		Document document = ddao.findById(form.getDocumentId());
		if (document != null) {
			document.setUrl(form.getUrl());
			ddao.update(document);
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
