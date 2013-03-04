package fr.univartois.ili.sadoc.ui.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.OperatorCreationException;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.ui.sign.IVerifSignFile;
import fr.univartois.ili.sadoc.ui.sign.VerifSignFileImpl;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class VerifyDocument extends ActionSupport {

	private static final int BUFFER_SIZE = 8192;

	private static final long serialVersionUID = 1L;

	private File file;
	private String id;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	public String execute() {

		Document document = metierUIServices.findDocumentById(id);
		if (document != null || file == null) {
			try {
				InputStream input = new FileInputStream(file);
				byte[] b = readFully(input);
				IVerifSignFile vsf = new VerifSignFileImpl();
				if (vsf.verifSignFile(b, document.getP7s())) {
					return SUCCESS;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (OperatorCreationException e) {
				e.printStackTrace();
			} catch (CertificateException e) {
				e.printStackTrace();
			} catch (CMSException e) {
				e.printStackTrace();
			}
		}
		return ERROR;
	}

	public byte[] readFully(InputStream stream) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];

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

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

}
