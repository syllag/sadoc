package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

@Service("wsPrivate")
public interface WSPrivate {
	
	boolean verifyDocument(Byte[] doc, Document document, Owner utilisateur);

	Map<Owner, List<Acquisition>> getDocumentInformations(long documentId);
	
	Document getDocument(long id);
	
	List<Document> importDocument(Owner owner);

	List<Competence> importCompetences(Document document);

}
