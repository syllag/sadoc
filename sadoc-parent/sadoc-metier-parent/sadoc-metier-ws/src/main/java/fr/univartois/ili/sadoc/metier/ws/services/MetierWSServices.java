package fr.univartois.ili.sadoc.metier.ws.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

// TODO utiliser les interfaces
import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerWSDAO;
import fr.univartois.ili.sadoc.dao.SignatureDAO;

import fr.univartois.ili.sadoc.metier.ws.vo.*;

//import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.metier.ws.utils.Mapper;


public class MetierWSServices implements IMetierWSServices {
	
	// XXX utile???
	public void createAcquisition(Acquisition acquisition) {
		AcquisitionDAO acquisitionDAO = new AcquisitionDAO();
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.create(acqui);
	}
	
	// XXX utile???
	public void createCertificate(Certificate certificate) throws SQLException, IOException {
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper.certificateVOToCertificateDO(certificate);
		certificateDAO.create(certif);
	}
	
	// XXX OK
	public void createDocument(Document document) {
		DocumentDAO documentDAO = new DocumentDAO();
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
		documentDAO.create(doc);
	}
	
	// XXX a faire
	public void createOwnerWS(Owner ownerWS) {
		// TODO revoir vo Owner
	}
	// XXX revoir : Owner ou OwnerWS ???
	public void createOwner(Owner owner) {
		// TODO
	}
	
	// XXX OK
	public void createSignature(Signature signature) {
		SignatureDAO signatureDAO = new SignatureDAO();
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper.signatureVOToSignatureDO(signature);
		signatureDAO.create(sign);
	}
	
	// XXX OK
	public void updateDocument(Document document) {
		DocumentDAO documentDAO = new DocumentDAO();
//		fr.univartois.ili.sadoc.dao.entities.Document docOld = Mapper.documentVOToDocumentDO(findDocumentById(document.getId()));
//		fr.univartois.ili.sadoc.dao.entities.Document docNew = Mapper.documentVOToDocumentDO(document);
//		docNew.setId(docOld.getId());
//		documentDAO.update(docNew);
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
		documentDAO.update(doc);
	}
	
	// XXX utile???
	public void updateAcquisition(Acquisition acquisition) {
		AcquisitionDAO acquisitionDAO = new AcquisitionDAO();
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.update(acqui);
	}
		
	// XXX utile???
	public void updateCertificate(Certificate certificate) throws SQLException, IOException {
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper.certificateVOToCertificateDO(certificate);
		certificateDAO.update(certif);
	}
	
	// XXX utile???
	public void updateSignature(Signature signature) {
		SignatureDAO signatureDAO = new SignatureDAO();
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper.signatureVOToSignatureDO(signature);
		signatureDAO.update(sign);
	}
	
	// XXX OK
	public Document findDocumentById(long id) {
		Document document = new Document();
		DocumentDAO documentDAO = new DocumentDAO();
		document = Mapper.documentDOToDocumentVO(documentDAO.findById(id));
		return document;
	}
	
	
	
	
	// XXX a voir avec la suivante
	public Certificate findCertificateByOwner(Owner owner) {
		Certificate certificate = new Certificate();
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS = ownerDOToOwnerVO(owner);
		certificate = certificateDOToCertificateVO(certificateDAO.findByOwner(ownerWS));
		return certificate;
	}

	// XXX a voir avec la précédente
	public List<Certificate> findCertificatesByOwner(Owner owner) {
		List<Certificate>certificates = new ArrayList<Certificate>();
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS = ownerDOToOwnerVO(owner);
		certificates = certificateDOToCertificateVO(certificateDAO.findByOwner(ownerWS));
		return certificates;
	}
	
	
	
	
	
	public Owner findOwnerByDocument(Document document) {
		Owner owner = new Owner();
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		owner = mapperOwnerDOToOwnerVO(ownerWSDAO.findByDocument(document));
		return owner;
	}

	

	public List<Document> findDocumentByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	public Owner findOwnerByMail(String mail) {
		Owner owner = new Owner();
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		owner = mapperOwnerDOToOwnerVO(ownerWSDAO.findByMail(mail));
		return owner;
	}

	
	// XXX Plus nécessaire car pas Competence dans la BDws
//	public void createCompetence(Competence competence) {
//		ICompetenceDAO competenceDAO;
//		fr.univartois.ili.sadoc.dao.entities.Competence competence2 = mapperCompetencesVOToCompetencesDO(competence);
//		competenceDAO.create(competence2);
//	}
//	public Competence findCompetenceByAcronym(String acronym) {
//		Competence competence = new Competence();
//		CompetenceDAO competenceDAO = new CompetenceDAO();
//		competence = mapperOwnerDOToOwnerVO(competenceDAO.findByAcronym(acronym));
//		return competence;
//	}
//	public List<Competence> findCompetencesByDocument(Document document) {
//		List<Competence> competences = new ArrayList<Competence>();
//		List<CompetenceDAO> competencesDAO = new ArrayList<CompetenceDAO>();
//		competences = mapperCompetencesDOToCompetencesVO(competencesDAO.findCompetencesByDocument(document));
//		return competences;
//	}

}
