package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Certificate;


/**
 * @author Mouloud Goutal <mouloud.goutal@gmail.com>
 *
 */
public interface ICertificate {

	
	
	/**
	 * Certificate in database
	 * 
	 * @param certificate
	 */
	void createCertificate(Certificate certificate);
	
	/**
	 * Ask a Certificate identified by an id
	 * 
	 * @param id
	 * @return the corresponding Certificate
	 */
	Certificate findCertificateById(int id);
}
