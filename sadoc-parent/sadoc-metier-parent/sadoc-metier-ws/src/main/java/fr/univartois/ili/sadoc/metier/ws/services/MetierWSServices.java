package fr.univartois.ili.sadoc.metier.ws.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;


public class MetierWSServices implements IMetierWSServices {

	public void createCompetance(Competence competence) {
	CompetenceDAO competenceDAO;
	fr.univartois.ili.sadoc.domaine.Competence competence2 = mapperCompetencesVOToCompetencesDO(competence);
	competenceDAO.create(competence2);

	}

	public Document findDocumentById(int id) {
		Document document = new Document();
		DocumentDAO documentDAO = new DocumentDAO();
		document = mapperDocumentDOToDocumentVO(documentDAO.findById(id));
		 return document;
	}

	public Owner findOwnerByDocument(Document document) {
		Owner owner = new Owner();
		OwnerDAO ownerDAO = new OwnerDAO();
		owner = mapperOwnerDOToOwnerVO(ownerDAO.findByDocument(document));
		return owner;
	}

	public List<Competence> findCompetencesByDocument(Document document) {
		List<Competence> competences = new ArrayList<Competence>();
		List<CompetenceDAO> competencesDAO = new ArrayList<CompetenceDAO>();
		competences = mapperCompetencesDOToCompetencesVO(competencesDAO.findCompetencesByDocument(document));
		return competences;
	}

	public List<Document> findDocumentByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	public Owner findOwnerByMail(String mail) {
		Owner owner = new Owner();
		OwnerDAO ownerDAO = new OwnerDAO();
		owner = mapperOwnerDOToOwnerVO(ownerDAO.findByMail(mail));
		return owner;
	}

	public void createOwner(Owner owner) {
		// TODO Auto-generated method stub

	}

	public void updateOwner(Owner owner) {
		// TODO Auto-generated method stub

	}

	public void createDocument(Document document) {
		// TODO Auto-generated method stub

	}

	public Competence findCompetenceByAcronym(String acronym) {
		Competence competence = new Competence();
		CompetenceDAO competenceDAO = new CompetenceDAO();
		competence = mapperOwnerDOToOwnerVO(competenceDAO.findByAcronym(acronym));
		return competence;
	}

	public Certificate findCertificateByOwner(Owner owner) {
		Certificate certificate = new Certificate();
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.domaine.Owner ownerDAO= mapperOwnerDOToOwnerVO(owner);
		certificate = mapperCertificateDOToCertificateVO(certificateDAO.findByOwner(ownerDAO));
		return certificate;
	}

	public void createSignature(Signature signature) {
		// TODO Auto-generated method stub

	}

	public void updateDocument(Document document) {
		// TODO Auto-generated method stub

	}

	public List<Certificate> findCertificatesByOwner(Owner owner) {
		List<Certificate>certificates = new ArrayList<Certificate>();
		List<CertificateDAO> certificateDAOs = new ArrayList<CertificateDAO>();
		fr.univartois.ili.sadoc.domaine.Owner ownerDAO= mapperOwnerDOToOwnerVO(owner);
		certificates = mapperCompetencesDOToCompetencesVO(certificateDAOs.findCertificatesByOwner(ownerDAO));
		return certificates;
	}

}
