package fr.univartois.ili.sadoc.sadocweb.spring;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Signature;
import fr.univartois.ili.sadoc.sadocweb.pdf.ManageQRCImpl;
import fr.univartois.ili.sadoc.sadocweb.sign.integrationsign.SignFile;
import fr.univartois.ili.sadoc.sadocweb.utils.Crypt;
import fr.univartois.ili.sadoc.sadocweb.utils.Properties;

public class WSPublicImpl implements WSPublic {

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner createOwner(String lastName, String firstName, String mail)
			throws Exception {
		Owner owner = new Owner(firstName, lastName, mail);
		OwnerDAO.create(owner);
		return owner;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Competence[] competence) {
		Certificate certificate = getCertificate(owner).get(0);
		Document document = new Document(name, "", null);
		DocumentDAO.create(document);
		for (Competence comp : competence) {
			Signature signature = new Signature(document,
					certificate.getOwner(), comp, certificate);
			SignatureDAO.create(signature);
		}
		String url = Properties.URL + "/checkDocument?sa="
				+ Crypt.createFalseID(document.getId());
		ManageQRCImpl qrc = new ManageQRCImpl();
		byte[] dest = null;
		try {
			String file = qrc.generatePdfWithQrCode(new PdfReader(doc), url);
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			fis.read(b);
			SignFile sf = new SignFile();
			byte[] p7s = sf.signDocument(dest, owner);
			document.setPk7(p7s);
			DocumentDAO.update(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dest;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name,
			Certificate certificate, Competence[] competence) {
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createCertificate(Owner owner) {
		// Generation clefs publiques & prives
		// Certificate certificate = new Certificate("publicKey", "privateKey",
		// owner);
		// CertificateDAO.create(certificate);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Certificate> getCertificate(Owner owner) {
		List<Certificate> certificates = CertificateDAO.findByOwner(owner);
		return certificates;
	}
}
