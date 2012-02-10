package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class WSPrivateImpl implements WSPrivate {
	
	
	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public boolean verifyDocument(Byte[] doc, Certificate certificat,
			Owner utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Document> importDocument(Owner owner) {
		List<Document> documents = SignatureDAO.findDocumentByOwner(owner);
		return documents;
	}

	@Transactional (propagation=Propagation.REQUIRED, readOnly=true)
	public List<Competence> importCompetences(Document document) {
		List<Competence> competences = SignatureDAO.findCompetenceByDocument(document);
		return competences;
	}

}
