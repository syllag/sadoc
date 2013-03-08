package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;

public interface IDocumentDAO extends IWebServiceDAO<Document> {

	/**
	 * Find the List<document> by OwnerWS
	 * 
	 * @param ownerWS
	 * @return the List<Document>
	 */
	List<Document> findByOwnerWS(OwnerWS ownerWS);
}
