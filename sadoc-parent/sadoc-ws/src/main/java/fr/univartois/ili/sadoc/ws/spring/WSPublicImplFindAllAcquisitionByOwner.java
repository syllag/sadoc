package fr.univartois.ili.sadoc.ws.spring;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;
import fr.univartois.ili.sadoc.metier.ws.services.IMetierWSServices;
import fr.univartois.ili.sadoc.metier.ws.services.IMetierWSServicesFindAllAcquisitionByOwner;
import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;
import fr.univartois.ili.sadoc.ws.pdf.ManageQRCImpl;
import fr.univartois.ili.sadoc.ws.sign.integrationsign.SignFile;
import fr.univartois.ili.sadoc.ws.utils.Crypt;

public class WSPublicImplFindAllAcquisitionByOwner implements WSPublicFindAllAcquisitionByOwner {

	@Autowired
	private IMetierWSServicesFindAllAcquisitionByOwner metierWSServices;
	
	@Autowired
	private IMetierCommunServices metierCommunServices;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner createOwner(String mail)
			throws Exception {
		Owner owner = metierWSServices.findOwnerByMail(mail);
		if (owner == null) {
			owner = new Owner(mail);
			metierWSServices.createOwnerWS(owner);
		}
		return owner;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Acquisition[] acquisition) {
		byte[] dest = null;
		try {
			SignFile sf = new SignFile();
			Owner ownOwner = metierWSServices.findOwnerByMail(owner.getMail_initial());
			if (ownOwner == null) {
				throw new IllegalStateException("L'utilisateur n'existe pas !");
				//ownerDAO.create(owner);
			}

			//TODO if certificate is null
			Certificate certificate = metierWSServices.findCertificateByOwner(ownOwner);
			if(certificate == null){
				certificate = sf.GiveCertificateForUser(ownOwner);
			}
			metierWSServices.updateOwnerWS(ownOwner);

			Document document = new Document(name, "", null);
			for (Acquisition acq : acquisition) {				
				if(metierCommunServices.isValideAcronym(acq.getId_item())){
					Acquisition a = metierWSServices.findAcquisitionByAcronym(acq.getId_item());
					if(acquisition == null){
						a = new Acquisition();
						a.setId_item(acq.getId_item());
						a.setCreationDate(new Date());
						metierWSServices.createAcquisition(acq);
					}
					a.getDocuments().add(document);
					document.getAcquisitions().add(a);
				} else {
					//TODO : Log 
					// problème la compétence n'existe pas
				}
			}
			metierWSServices.createDocument(document);
			for(Acquisition a : document.getAcquisitions()){
				metierWSServices.updateAcquisition(a);
			}			
				Signature signature = new Signature(document, certificate);
				metierWSServices.createSignature(signature);

				ManageQRCImpl qrc = new ManageQRCImpl();

			try {
				dest = qrc.generatePdfWithQrCode(new PdfReader(doc),
						String.valueOf(Crypt.createFalseID(document.getId())));

				// XXX : A voir avec Anicet comment faire pour le récupérer ultérieurement
				byte[] p7s = sf.signDocument(dest, ownOwner, certificate);
				document.setP7s(p7s);
				
				metierWSServices.updateDocument(document);

			} catch (Exception e) {
				// TODO log
				e.printStackTrace();
			}
		} catch (Exception e1) {
			// TODO log
			e1.printStackTrace();
		}
		return dest;
	}

	// XXX : ??? 
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public byte[] signDocument(byte[] doc, String name,
			Certificate certificate, Competence[] competence) {
		return null;
	}

	// XXX : ?? 
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createCertificate(Owner owner) {
		// Generation clefs publiques & prives
		// Certificate certificate = new Certificate("publicKey", "privateKey",
		// owner);
		// CertificateDAO.create(certificate);
	}

	// @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Certificate> getCertificate(Owner owner) {
		return metierWSServices.findCertificatesByOwner(owner);
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
		return metierWSServices.findOwnerByMail(mail);
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

	@Override
	public List<Acquisition> findAllAcquisition(Owner utilisateur) {
		return metierWSServices.findAllAcquisition(utilisateur);
	}
}
