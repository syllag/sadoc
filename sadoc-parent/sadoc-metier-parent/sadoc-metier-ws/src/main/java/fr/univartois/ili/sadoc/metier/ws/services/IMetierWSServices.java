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
public interface IMetierWSServices {
    //TODO JavaDoc
	//TODO voir doublons méthode avec partie ui
	// Les méthodes DAO de la partie ui devrait devenir
	// quasi inexistante et tout doit se faire par 
	// appel webservice
	
	void createCompetance(Competence competence);
	
	Document findDocumentById(int id);
	
	Owner findOwnerByDocument(Document document);

	List<Competence> findCompetenceByDocument();
	
	List<Document> findDocumentByOwner(Owner owner);
	
	Owner findOwnerByMail(String mail);
	
	void createOwner(Owner owner);
	
	void updateOwner(Owner owner);
	
	void createDocument(Document document);
	
	Competence findCompetenceByAcronym(String acronym);
	
	Certificate findCertificateByOwner(Owner owner);
	
	void createSignature(Signature signature);
	
	void updateDocument(Document document);
	
	List<Certificate> findCertificateByOwner();
}
