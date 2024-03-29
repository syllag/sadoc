package fr.univartois.ili.sadoc.metier.ws.services;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 * 
 */
public interface IMetierWSServicesFindAllAcquisitionByOwner {
	// TODO JavaDoc
	// TODO voir doublons méthode avec partie ui
	// Les méthodes DAO de la partie ui devrait devenir
	// quasi inexistante et tout doit se faire par
	// appel webservice

	// XXX Plus nécessaire car pas Competence dans la BDws
//	/**
//	 * @param competence
//	 */
//	void createCompetence(Competence competence);
	
	/**
	 * Create a document
	 * 
	 * @param document
	 * @return void
	 */
	void createDocument(Document document);
	
	/**
	 * 
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
	 * Create an acquisition
	 * 
	 * @param acquisition
	 */
	void createAcquisition(Acquisition acquisition);
	
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
	 *  
	 */
	void updateOwnerWS(Owner owner);
	
	/**
	 * Update an acquisition
	 * 
	 * @param acquisition
	 *  
	 */
	void updateAcquisition(Acquisition acquisition);
	

	/**
	 * Find a specific document
	 * 
	 * @param id
	 * @return Document
	 */
	Document findDocumentById(long id);

	/**
	 * @param document
	 * @return Owner
	 */
	Owner findOwnerByDocument(Document document);


	
	/**
	 * @param owner
	 * @return List<Document>
	 */
	List<Document> findDocumentByOwner(Owner owner);

	
	/**
	 * @param mail
	 * @return Owner
	 */
	Owner findOwnerByMail(String mail);
	
	/**
	 * @param owner
	 * @return Certificate
	 */
	Certificate findCertificateByOwner(Owner owner);

	/**
	 * @param owner
	 * @return List<Certificate>
	 */
	List<Certificate> findCertificatesByOwner(Owner owner);
	
	/**
	 * @param acronym
	 * @return Competence
	 */
	Competence findCompetenceByAcronym(String acronym); 
	
	/**
	 * @param document
	 * @return List<Competence>
	 */
	List<Competence> findCompetencesByDocument(Document document);
	
	/**
	 * 
	 * @return
	 */
	Map<Owner, List<Competence>> findOwnerAndCompetencesByDocumentId(int documentId);
	
	/**
	 * Find an acquisition by acronym (nb:  format idReferentiel:idDomaine:idCompetence:idItem)
	 * 
	 * @param idItem
	 * @return Acquisition
	 */
	Acquisition findAcquisitionByAcronym(String idItem);
	
	FileInputStream getP7SFromDocument(long id);
	
	/**
	 * Find all acquisition by owner
	 * 
	 * @param owner
	 * @return List<Acquisition>
	 */
	List<Acquisition> findAllAcquisition(Owner utilisateur);
	
}