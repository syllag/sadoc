package fr.univartois.ili.sadoc.sadocweb.spring;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

public class WSPublicImpl implements WSPublic {
	
	@Resource(name="ownerDAO")
	private OwnerDAO ownerDAO ;
	
	@Resource(name="documentDAO")
	private DocumentDAO documentDAO;
	
	@Resource(name="signatureDAO")
	private SignatureDAO signatureDAO;
	
	@Resource(name="certificateDAO")
	private CertificateDAO certificateDAO ;


//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner createOwner(String lastName, String firstName, String mail)
			throws Exception {
		Owner owner = new Owner(firstName, lastName, mail);
		ownerDAO.create(owner);

		return owner;
	}

	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Competence[] competence) {
		byte[] handler = null;
		byte[] pdfRequest=doc;
		try {
		FileOutputStream pdfFile=new FileOutputStream("C:/Users/hoss/Desktop/"+name+".pdf");
		
		pdfFile.write(pdfRequest);
		pdfFile.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			handler = loadFile("C:/olap.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler;
	}

	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name,
			Certificate certificate, Competence[] competence) {
		return null;
	}

	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createCertificate(Owner owner) {
		// Generation clefs publiques & prives
		// Certificate certificate = new Certificate("publicKey", "privateKey",
		// owner);
		// CertificateDAO.create(certificate);
	}

	//@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Certificate> getCertificate(Owner owner) {
		certificateDAO = new CertificateDAO();
		return certificateDAO.findByOwner(owner);
	}

////	public OwnerDAO getOwnerDAO() {
////		return ownerDAO;
////	}
////
////	@Autowired
////	public void setOwnerDAO(OwnerDAO ownerDAO) {
////		this.ownerDAO = ownerDAO;
////	}
//	
	
	public static byte[] loadFile(String sourcePath) throws IOException
	{
	    InputStream inputStream = null;
	    try 
	    {
	        inputStream = new FileInputStream(sourcePath);
	        return readFully(inputStream);
	    } 
	    finally
	    {
	        if (inputStream != null)
	        {
	            inputStream.close();
	        }
	    }
	}
	
	public static byte[] readFully(InputStream stream) throws IOException
	{
	    byte[] buffer = new byte[10000];
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    int bytesRead;
	    while ((bytesRead = stream.read(buffer)) != -1)
	    {
	        baos.write(buffer, 0, bytesRead);
	    }
	    return baos.toByteArray();
	}
}
