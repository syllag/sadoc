package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;


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
	Domaine findDomaineById(long id);

	/**
	 * Insert the domaine in DB
	 * 
	 * @param domaine that is the domaine to create 
	 */
	void createDomaine(Domaine domaine);
	
	/**
	 * Ask the list of Domaine by Referentiel
	 * 
	 * @param referentiel
	 * @return List<Domaine>
	 */
	List<Domaine> findDomaineByReferentiel(Referentiel referentiel);

}
