package fr.univartois.ili.sadoc.actions;

import org.postgresql.core.Utils;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.Form.ModifyUrlForm;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.utils.TestID;

public class ModifyUrl extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModifyUrlForm form;
	private Document document;
	private String id;
	
	public String execute() {
		
		DocumentDAO ddao = new DocumentDAO();
		
		if (document != null) {
			document.setUrl(form.getUrl());
			
			ddao.update(document);
			
			id = TestID.createFalseID(document.getId());
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

}
