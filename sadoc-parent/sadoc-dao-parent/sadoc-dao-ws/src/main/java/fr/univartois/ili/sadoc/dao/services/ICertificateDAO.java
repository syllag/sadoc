package fr.univartois.ili.sadoc.dao.services;

import java.security.acl.Owner;
import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Certificate;

public interface ICertificateDAO extends IWebServiceDAO<Certificate> {

	/**
	 * Get certificates associated to the owner 
	 * @param owner
	 * @return List of certificate
	 */
	List<Certificate> findByOwner(Owner owner);

}
