package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;

public interface IDocumentDAO extends IWebServiceDAO<Document> {
	/**
	 * Find a owner asssociated with a document
	 * @param document The document
	 * @return The owner of the document
	 */
	OwnerWS findOwnerWSByDocument(Document document);
}
