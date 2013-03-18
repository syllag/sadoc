package fr.univartois.ili.sadoc.metier.ws.services;

import java.io.FileInputStream;
import java.util.List;
import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 * 
 */
public interface IMetierWSServices {
	// TODO voir doublons méthode avec partie ui
	// Les méthodes DAO de la partie ui devrait devenir
	// quasi inexistante et tout doit se faire par
	// appel webservice
	
	/**
	 * Create an acquisition
	 * 
	 * @param acquisition
	 */
	void createAcquisition(Acquisition acquisition);
	
	/**
	 * Create a document
	 * 
	 * @param document
	 * @return void
	 */
	void createDocument(Document document);
	
	/**
	 * Create a ownerWS
	 * 
	 * @param owner
	 * @return void
	 */
	void createOwnerWS(Owner owner);
	
	/**
	 * Create a signature
	 * 
	 * @param signature
	 * @return void
	 */
	void createSignature(Signature signature);
	
	/**
	 * Update an acquisition
	 * 
	 * @param acquisition
	 */
	void updateAcquisition(Acquisition acquisition);
	
	/**
	 * Update a document
	 * 
	 * @param document
	 */
	void updateDocument(Document document);
	
	/**
	 * Update an owner
	 * 
	 * @param owner
	 */
	void updateOwnerWS(Owner owner);

	/**
	 * Find a specific document
	 * 
	 * @param id
	 * @return Document
	 */
	Document findDocumentById(long id);
	
	/**
	 * Find all the documents of a owner
	 * 
	 * @param owner
	 * @return List<Document>
	 */
	List<Document> findDocumentByOwner(Owner owner);

	/**
	 * Find the owner of a document
	 * 
	 * @param document
	 * @return Owner
	 */
	Owner findOwnerByDocument(Document document);
	
	/**
	 * Find a owner with his mail
	 * 
	 * @param mail
	 * @return Owner
	 */
	Owner findOwnerByMail(String mail);
	
	/**
	 * Find the certificate of the owner
	 * 
	 * @param owner
	 * @return Certificate
	 */
	Certificate findCertificateByOwner(Owner owner);

	/**
	 * Find all the certificates of the owner
	 * 
	 * @param owner
	 * @return List<Certificate>
	 */
	List<Certificate> findCertificatesByOwner(Owner owner);
	
	/**
	 * Find an acquisition by acronym (nb:  format idReferentiel:idDomaine:idCompetence:idItem)
	 * 
	 * @param idItem
	 * @return Acquisition
	 */
	Acquisition findAcquisitionByAcronym(String idItem);
	
	FileInputStream getP7SFromDocument(long id);
	
}