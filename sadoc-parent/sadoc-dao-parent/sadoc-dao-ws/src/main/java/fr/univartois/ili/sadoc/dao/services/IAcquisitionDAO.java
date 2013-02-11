package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Acquisition;
import fr.univartois.ili.sadoc.domaine.Document;


/**
 * @author Mouloud Goutal <mouloud.goutal@gmail.com>
 *
 */
public interface IAcquisitionDAO {

	/**
	 * Aquisition in database
	 * 
	 * @param Acquisition that is the Acquisition to create
	 */
	void createAcquisition(Acquisition acquisition);

	/**
	 * Ask the list of Acquisition by document
	 * 
	 * @param document
	 * @return List<Acquisition> that belongs to the document
	 */
	List<Acquisition> findAcquisitionByDocument(Document document);

	/**
	 * Ask an Acquisition identified by an id
	 * 
	 * @param id
	 * @return the corresponding Acquisition
	 */
	Acquisition findAcquisitionById(int id);

	/**
	 * Ask the list of Acquisition by owner
	 * 
	 * @param owner
	 * @return the List<Acquisition> that belongs to the owner
	 */
	List<Acquisition> findAcquisitionByOwner(int owner);
}
