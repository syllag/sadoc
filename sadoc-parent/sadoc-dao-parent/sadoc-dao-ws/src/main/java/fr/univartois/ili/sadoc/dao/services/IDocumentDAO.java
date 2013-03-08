package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;

public interface IDocumentDAO extends IWebServiceDAO<Document> {

	/**
	 * Find the document by OwnerWS
	 * 
	 * @param ownerWS
	 * @return the Document
	 */
	Document findByOwnerWS(OwnerWS ownerWS);
}
