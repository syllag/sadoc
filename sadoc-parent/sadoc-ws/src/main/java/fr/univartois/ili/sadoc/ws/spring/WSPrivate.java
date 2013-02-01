package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

@Service("wsPrivate")
public interface WSPrivate {
	
	boolean verifyDocument(Byte[] doc, Document document, Owner utilisateur);

	Map<Owner, List<Competence>> getDocumentInformations(int documentId);
	
	Document getDocument(int id);
	
	List<Document> importDocument(Owner owner);

	List<Competence> importCompetences(Document document);

}
