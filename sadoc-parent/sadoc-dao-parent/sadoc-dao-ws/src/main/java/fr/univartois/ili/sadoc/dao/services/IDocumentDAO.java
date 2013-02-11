package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Document;
 /**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface IDocumentDAO {
	/**
	 * Ask the document identified by id of the DB
	 * 
	 * @param id
	 * @return document
	 * return the corresponding document
	 */
	Document findDocumentById(String id);
	
	/**
	 * Insert the document in DB
	 * 
	 * @param document that is the document to create 
	 */
	void createDocument(Document document);
	
	/**
	 * Update the document in DB
	 * 
	 * @param document 
	 * the document is the document to update 
	 */
	void updateDocument(Document document);
	
}
