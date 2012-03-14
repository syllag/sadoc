package fr.univartois.ili.sadoc.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;

public class DownloadP7S extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sa = null;
	private InputStream fileInputStream;

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
		//long realID = TestID.findRealID(sa);
		long realID = Long.valueOf(sa);
		DocumentDAO ddao = new DocumentDAO();
		Document doc = ddao.findById((int) realID);
		if (doc == null) {
			return ERROR;
		}
		FileOutputStream envfos = new FileOutputStream("/home/guillaume/testDownload.p7s");
		envfos.write(doc.getP7s());
		envfos.close();
	    fileInputStream = new FileInputStream(new File("/home/guillaume/testDownload.p7s"));
		return SUCCESS;
	}

}
