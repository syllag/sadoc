package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Acquisition;
import fr.univartois.ili.sadoc.dao.entities.Document;

public interface IAcquisitionDAO{

	/**
	 * Ask the list of Acquisition by document
	 * 
	 * @param document
	 * @return List<Acquisition> that belongs to the document
	 */
	List<Acquisition> findAcquisitionByDocument(Document document);

	/**
	 * Ask the list of Acquisition by owner
	 * 
	 * @param owner
	 * @return the List<Acquisition> that belongs to the owner
	 */
	List<Acquisition> findAcquisitionByOwner(int owner);
}
