package fr.univartois.ili.sadoc.sadocweb.spring;

import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;
import fr.univartois.ili.sadoc.sadocweb.pdf.ManageQRCImpl;
import fr.univartois.ili.sadoc.sadocweb.sign.integrationsign.*;
import fr.univartois.ili.sadoc.sadocweb.utils.Crypt;
import fr.univartois.ili.sadoc.sadocweb.utils.Properties;

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
		 SignFile sf = new SignFile();
		System.out.println("SIGNDOCUMENT OK !");
        Owner ownOwner= ownerDAO.findByMail(owner.getMail());
        Certificate certificate=null;
		try {
			certificate = sf.GiveCertificateForUser(ownOwner);
			List<Certificate> ltmp =ownOwner.getCertificates();
			ltmp.add(certificate);
			owner.setCertificates(ltmp);
			certificateDAO.create(certificate);
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
        System.out.println("GET CERTIF OK !");
        Document document = new Document(name, "", null);
        documentDAO.create(document);
        System.out.println("CREATE DOC OK !");
        Competence compTmp=null;
        for (Competence comp : competence) {
            compTmp=competenceDAO.findByAcronym(comp.getAcronym());
            Signature signature = new Signature(document,
                    certificate.getOwner(), compTmp, certificate);
            signatureDAO.create(signature);
        }
        String url = Properties.URL + "/checkDocument?sa="
                + Crypt.createFalseID(document.getId());
        ManageQRCImpl qrc = new ManageQRCImpl();
        byte[] dest = null;
        System.out.println("DAO OK !");
        try {
            dest = qrc.generatePdfWithQrCode(new PdfReader(doc), url);
            System.out.println("Generate PDFQRCODE OK !");
            FileInputStream fis = new FileInputStream(name);
            byte[] b = new byte[(int) dest.length];
            fis.read(b);
           
            System.out.println("SIGNFILE OK !");
            byte[] p7s = sf.signDocument(dest, ownOwner);
            System.out.println("SIGNDOC OK !");
            document.setPk7(p7s);
            documentDAO.update(document);
            
            System.out.println("PDF + QRCODE OK !");
            
            fis.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
//		return doc;
           
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
}