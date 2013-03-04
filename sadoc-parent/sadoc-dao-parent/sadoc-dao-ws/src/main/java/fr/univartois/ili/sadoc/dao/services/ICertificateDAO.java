package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Certificate;

public interface ICertificateDAO extends IWebServiceDAO<Certificate> {

	/**
	 * Get certificates associated to the owner 
	 * @param owner
	 * @return List of certificate
	 */
	List<Certificate> findByOwner(OwnerWS owner);

}
