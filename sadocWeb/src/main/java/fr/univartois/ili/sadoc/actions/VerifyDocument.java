package fr.univartois.ili.sadoc.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.sign.IVerifSignFile;
import fr.univartois.ili.sadoc.sign.VerifSignFileImpl;

public class VerifyDocument extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;
	private String id;
	
	public String execute() {
		DocumentDAO ddao = new DocumentDAO();
		Document document = ddao.findById(id);
		if (document != null) {
			try {				
				InputStream input= new FileInputStream(file);
				byte[] b = readFully(input);
				IVerifSignFile vsf = new VerifSignFileImpl();
				if (vsf.verifSignFile(b, document.getP7s())){
					return SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ERROR;
	}

	public byte[] readFully(InputStream stream) throws IOException {
        byte[] buffer = new byte[8192];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
