package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;


public interface IOwnerWSDAO extends IWebServiceDAO<OwnerWS> {

	/**
	 * Find a Owner with this email address
	 * @param mail the owner email
	 * @return The owner
	 */
	OwnerWS findByMail(String mail);

	/**
	 * Find a Owner with this document
	 * @param document
	 * @return The owner
	 */
	OwnerWS findByDocument(Document document);
	
}
