package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.entitiesws.classes.Competence;
import fr.univartois.ili.sadoc.entitiesws.classes.Document;
import fr.univartois.ili.sadoc.entitiesws.classes.Owner;

@Service("wsPrivate")
public interface WSPrivate {
	
	boolean verifyDocument(Byte[] doc, Document document, Owner utilisateur);

	Map<Owner, List<Competence>> getDocumentInformations(int documentId);
	
	Document getDocument(int id);
	
	List<Document> importDocument(Owner owner);

	List<Competence> importCompetences(Document document);

}
