package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Referentiel;

/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface IReferentielDAO {
	/**
	 * Ask the Referentiel identified by id of the Database
	 * 
	 * @param id
	 * @return Referentiel
	 * return the corresponding referentiel
	 */
	Referentiel findReferentielById(long id);

	/**
	 * Insert the referentiel in DB
	 * 
	 * @param competence that is the referentiel to create 
	 */
	void createReferentiel(Referentiel referentiel);

}
