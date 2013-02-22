package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Certificate;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.entities.Signature;

public interface ISignatureDAO extends IWebServiceDAO<Signature> {

	/**
	 * Find documents for the owner
	 * @param owner The owner
	 * @return His documents
	 */
	List<Document> findDocumentByOwnerWS(OwnerWS owner);

	/**
	 * Returns signatures done by a certificate
	 * @param certificate The certificates
	 * @return All signatures done by the certificate
	 */
	List<Signature> findByCertificate(Certificate certificate);

	/**
	 * Returns the signatures used on  a document.
	 * @param document The document
	 * @return List of signatures
	 */
	List<Signature> findByDocument(Document document);

	/**
	 * Returns signatures done by a owner
	 * @param owner The owner
	 * @return The list of signatures
	 */
	List<Signature> findByOwnerWS(OwnerWS owner);

}
