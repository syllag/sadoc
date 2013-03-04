package fr.univartois.ili.sadoc.metier.ws.services;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 * 
 */
/**
 * @author mathieu15
 *
 */
public interface IMetierWSServices {
	// TODO JavaDoc
	// TODO voir doublons méthode avec partie ui
	// Les méthodes DAO de la partie ui devrait devenir
	// quasi inexistante et tout doit se faire par
	// appel webservice

	/**
	 * @param competence
	 */
	void createCompetance(Competence competence);

	/**
	 * @param id
	 * @return Document
	 */
	Document findDocumentById(int id);

	/**
	 * @param document
	 * @return Owner
	 */
	Owner findOwnerByDocument(Document document);


	/**
	 * @param document
	 * @return List<Competence>
	 */
	List<Competence> findCompetencesByDocument(Document document);

	
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
	 * @return void
	 */
	void createOwner(Owner owner);

	/**
	 * @param owner
	 * @return void
	 */
	void updateOwner(Owner owner);

	
	/**
	 * @param document
	 * @return void
	 */
	void createDocument(Document document);

	
	/**
	 * @param acronym
	 * @return Competence
	 */
	
	Competence findCompetenceByAcronym(String acronym); 
	
	
	/**
	 * @param owner
	 * @return Certificate
	 */
	Certificate findCertificateByOwner(Owner owner);

	
	/**
	 * @param signature
	 * @return void
	 */
	void createSignature(Signature signature);

	
	/**
	 * @param document
	 * @return void
	 */
	void updateDocument(Document document);

	/**
	 * @param owner
	 * @return List<Certificate>
	 */
	List<Certificate> findCertificatesByOwner(Owner owner);
}
