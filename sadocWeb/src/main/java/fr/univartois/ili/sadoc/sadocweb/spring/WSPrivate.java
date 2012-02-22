package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public interface WSPrivate {
	
	boolean verifyDocument(Byte[] doc, Document document, Owner utilisateur);

	Map<Owner, List<Competence>> getDocumentInformations(int documentId);
	
	List<Document> importDocument(Owner owner);

	List<Competence> importCompetences(Document document);

}