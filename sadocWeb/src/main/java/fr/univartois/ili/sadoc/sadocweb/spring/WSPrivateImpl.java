package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

public class WSPrivateImpl implements WSPrivate {
	
	
	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public boolean verifyDocument(Byte[] doc, Document document,
			Owner utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public Map<Owner, List<Competence>> getDocumentInformations(int documentId) {
		Map<Owner, List<Competence>> info = new HashMap<Owner, List<Competence>>();
		Document document = DocumentDAO.findById(documentId);
		Owner owner = SignatureDAO.findOwnerByDocument(document);
		List<Competence> competences = SignatureDAO.findCompetenceByDocument(document);
		info.put(owner, competences);
		return info;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Document> importDocument(Owner owner) {
		List<Document> documents = SignatureDAO.findDocumentByOwner(owner);
		Set<Document> docs=new HashSet<Document>(documents);
		documents= new ArrayList<Document> (docs);
		return documents;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Competence> importCompetences(Document document) {
		List<Competence> competences = SignatureDAO.findCompetenceByDocument(document);
		return competences;
	}

	public Document getDocument(int id) {
		Document document = DocumentDAO.findById(id);
		return document;
	}

}
