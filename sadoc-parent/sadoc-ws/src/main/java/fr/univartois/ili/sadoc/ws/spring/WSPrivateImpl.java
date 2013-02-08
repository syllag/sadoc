package fr.univartois.ili.sadoc.ws.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.entitiesws.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.SignatureDAO;
//TODO : replace DAO
public class WSPrivateImpl implements WSPrivate {
	
	@Resource(name="ownerDAO")
	private OwnerDAO ownerDAO ;
	
	@Resource(name="documentDAO")
	private DocumentDAO documentDAO;
	
	@Resource(name="signatureDAO")
	private SignatureDAO signatureDAO;
	
	@Resource(name="certificateDAO")
	private CertificateDAO certificateDAO ;
	
	@Resource(name="competenceDAO")
	private CompetenceDAO competenceDAO ;
	
	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public boolean verifyDocument(Byte[] doc, Document document,
			Owner utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public Map<Owner, List<Competence>> getDocumentInformations(int documentId) {
		Map<Owner, List<Competence>> info = new HashMap<Owner, List<Competence>>();
		//documentDAO= new DocumentDAO();
		//signatureDAO = new SignatureDAO();
		Document document = documentDAO.findById(documentId);
		Owner owner = signatureDAO.findOwnerByDocument(document);
		List<Competence> competences = signatureDAO.findCompetenceByDocument(document);
		info.put(owner, competences);
		return info;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Document> importDocument(Owner owner) {
		//TODO prévoir un distinct
		signatureDAO = new SignatureDAO();
		List<Document> documents = signatureDAO.findDocumentByOwner(owner);
		Set<Document> docs=new HashSet<Document>(documents);
		documents= new ArrayList<Document> (docs);
		return documents;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Competence> importCompetences(Document document) {
		signatureDAO = new SignatureDAO();
		List<Competence> competences = signatureDAO.findCompetenceByDocument(document);
		return competences;
	}


	public Document getDocument(int id) {
		Document document = documentDAO.findById(id);
		return document;
	}

}