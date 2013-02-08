package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Signature;


/**
 * @author Mouloud Goutal <mouloud.goutal@gmail.com>
 *
 */
public interface ISignature {

	
	
	/**
	 * Signature in database
	 * 
	 * @param signature
	 */
	void createSignature(Signature signature);
	

	/**
	 * Ask a Signature identified by an id
	 * 
	 * @param id
	 * @return the corresponding Signature
	 */
	Signature findSignatureById(int id);
}
