package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.metier.ws.services.IMetierWSServices;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

public class WSPrivateImpl implements WSPrivate {

	@Autowired
	private IMetierWSServices metierWSServices;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean verifyDocument(Byte[] doc, Document document,
			Owner utilisateur) {
		// TODO imp method ?
		return false;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<Owner, List<Competence>> getDocumentInformations(int documentId) {
		
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Document> importDocument(Owner owner) {
		return metierWSServices.findDocumentByOwner(owner);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Competence> importCompetences(Document document) {
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Document getDocument(int id) {
		return metierWSServices.findDocumentById(id);
	}

}
