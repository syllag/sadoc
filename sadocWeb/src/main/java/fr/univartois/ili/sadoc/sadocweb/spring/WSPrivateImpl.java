package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class WSPrivateImpl implements WSPrivate {
	
	public boolean verifyDocument(Byte[] doc, Certificate certificat,
			Owner utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Document> importDocument(Owner owner) {
		List<Document> documents = SignatureDAO.findDocumentByOwner(owner);
		Set<Document> docs=new HashSet(documents);
		documents= new ArrayList<Document> (docs);
		return documents;
	}

	public List<Competence> importCompetences(Document document) {
		List<Competence> competences = SignatureDAO.findCompetenceByDocument(document);
		return competences;
	}

}
