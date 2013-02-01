package fr.univartois.ili.sadoc.ws.spring;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;
import fr.univartois.ili.sadoc.entitiesws.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.SignatureDAO;
import fr.univartois.ili.sadoc.ws.pdf.ManageQRCImpl;
import fr.univartois.ili.sadoc.ws.sign.integrationsign.SignFile;
import fr.univartois.ili.sadoc.ws.utils.Crypt;
//TODO Change DAO
public class WSPublicImpl implements WSPublic {

	@Resource(name = "ownerDAO")
	private OwnerDAO ownerDAO;

	@Resource(name = "documentDAO")
	private DocumentDAO documentDAO;

	@Resource(name = "signatureDAO")
	private SignatureDAO signatureDAO;

	@Resource(name = "certificateDAO")
	private CertificateDAO certificateDAO;

	@Resource(name = "competenceDAO")
	private CompetenceDAO competenceDAO;

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner createOwner(String lastName, String firstName, String mail)
			throws Exception {
		Owner owner = ownerDAO.findByMail(mail);
		if (owner == null) {
			owner = new Owner(firstName, lastName, mail);
			ownerDAO.create(owner);
		}
		return owner;
	}

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Competence[] competence) {
		byte[] dest = null;
		try {
			SignFile sf = new SignFile();
			if (ownerDAO.findByMail(owner.getMail()) == null) {
				ownerDAO.create(owner);
			}
			Owner ownOwner = ownerDAO.findByMail(owner.getMail());

			Certificate certificate = sf.GiveCertificateForUser(ownOwner);
			certificate.setOwner(ownOwner);

			ownOwner.getCertificates().add(certificate);

			ownerDAO.update(ownOwner);
			Document document = new Document(name, "", null);
			documentDAO.create(document);
			Competence compTmp = null;
			for (Competence comp : competence) {
				compTmp = competenceDAO.findByAcronym(comp.getAcronym());
				Signature signature = new Signature(document, ownOwner,
						compTmp, certificateDAO.findByOwner(ownOwner).get(0));
				signatureDAO.create(signature);
			}
			ManageQRCImpl qrc = new ManageQRCImpl();

			try {
				dest = qrc.generatePdfWithQrCode(new PdfReader(doc),
						String.valueOf(Crypt.createFalseID(document.getId())));

				byte[] p7s = sf.signDocument(dest, ownOwner);
				document.setPk7(p7s);
				documentDAO.update(document);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return dest;
	}

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name,
			Certificate certificate, Competence[] competence) {
		return null;
	}

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createCertificate(Owner owner) {
		// Generation clefs publiques & prives
		// Certificate certificate = new Certificate("publicKey", "privateKey",
		// owner);
		// CertificateDAO.create(certificate);
	}

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Certificate> getCertificate(Owner owner) {

		return certificateDAO.findByOwner(ownerDAO.findByMail(owner.getMail()));
	}

	// // public OwnerDAO getOwnerDAO() {
	// // return ownerDAO;
	// // }
	// //
	// // @Autowired
	// // public void setOwnerDAO(OwnerDAO ownerDAO) {
	// // this.ownerDAO = ownerDAO;
	// // }
	//
	public Owner getOwner(String mail) {
		// TODO Auto-generated method stub
		return ownerDAO.findByMail(mail);
	}

	public static byte[] readFully(InputStream stream) throws IOException {
		byte[] buffer = new byte[8192];

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int bytesRead;
		while ((bytesRead = stream.read(buffer)) != -1) {
			baos.write(buffer, 0, bytesRead);
		}
		return baos.toByteArray();
	}

	public static byte[] loadFile(String sourcePath) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(sourcePath);
			return readFully(inputStream);
		}

		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
