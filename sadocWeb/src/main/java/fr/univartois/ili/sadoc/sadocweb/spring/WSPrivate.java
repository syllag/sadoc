package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;

public interface WSPrivate {
	
	boolean verifyDocument(Byte[] doc, Document document, Owner utilisateur);

	Map<Owner, List<Competence>> getDocumentInformations(int documentId);
	
	List<Document> importDocument(Owner owner);

	List<Competence> importCompetences(Document document);

}
