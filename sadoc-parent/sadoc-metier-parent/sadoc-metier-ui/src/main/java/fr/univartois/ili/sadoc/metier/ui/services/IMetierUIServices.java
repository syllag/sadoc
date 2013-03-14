/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ui.services;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;


/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
public interface IMetierUIServices {

	/**
	 * Insert the owner in Database
	 * 
	 * @param doc
	 */
	void createOwner(Owner owner);
	
	/**
	 * Update the owner in Database
	 * 
	 * @param owner
	 */
	void updateOwner(Owner owner);
	
	/**
	 * Ask the owner to the DB
	 * 
	 * @param idOwner
	 * @return
	 */
	Owner findOwnerById(long idOwner);
	
	/**
	 * Ask the owner by email
	 * 
	 * @param email
	 * @return
	 */
	Owner findOwnerByEmail(String email);
	
	/**
	 * Ask the owner authenticate by email and password in database
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	Owner findOwnerByEmailAndPassword(String email, String password);
	
	/**
	 * Ask the competence to the DB
	 * 
	 * @param id
	 * @return
	 */
	Competence findCompetenceById(int id);

	/**
	 * Insert the competence in Database
	 * 
	 * @param competence
	 */
	void createCompetence(Competence competence);
	
	/**
	 * Insert the acquisition in database
	 * 
	 * @param acquisition
	 */
	void createAcquisition(Acquisition acquisition);
	
	/**
	 * Insert the document in Database
	 * 
	 * @param doc
	 */
	void createDocument(Document document);
	
	/**
	 * Ask the document to the DB
	 * 
	 * @param id
	 * @return
	 * 		the document authenticate by id
	 */
	Document findDocumentById(String id);
	
	/**
	 * Ask the list of acquisition by document
	 * 
	 * @param document
	 * @return
	 */
	List<Acquisition> findAcquisitionByDocument(Document document);
	
	/**
	 * Ask the list of acquisition by owner
	 * 
	 * @param owner
	 * @return
	 */
	List<Acquisition> findAcquisitionByOwner(Owner owner);
	
	/**
	 * Update the document in database
	 * 
	 * @param document
	 */
	void updateDocument(Document document);
	
	
	/**
	 * Insert the resume in Database
	 * 
	 * @param resume
	 */
	void createResume(Resume resume);
	
	/**
	 * Update the resume in Database
	 * 
	 * @param resume
	 */
	void updateResume(Resume resume);
	
	/**
	 * Ask the Resume to the DB
	 * 
	 * @param id
	 * @return
	 * 		
	 */
	Resume findResumeById(int id);
	
	/**
	 * Ask the list of resume by owner
	 * 
	 * @param owner
	 * @return
	 */
	List<Resume> findResumeByOwner(Owner owner);
}
