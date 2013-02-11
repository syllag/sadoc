package fr.univartois.sadoc.dao.services;

import java.util.List;

import fr.univartois.sadoc.dao.entities.Referentiel;
import fr.univartois.sadoc.dao.entities.Domaine;


/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface IDomaineDAO {
	/**
	 * Ask the Domaine identified by id of the Database
	 * 
	 * @param id
	 * @return Domaine
	 * return the corresponding domaine
	 */
	Domaine findDomaineById(int id);

	/**
	 * Insert the domaine in DB
	 * 
	 * @param competence that is the domaine to create 
	 */
	void createDomanine(Domaine domaine);
	
	/**
	 * Ask the list of Domaine by Referentiel
	 * 
	 * @param referentiel
	 * @return List<Domaine>
	 */
	List<Domaine> findDomaineByReferentiel(Referentiel referentiel);

}
