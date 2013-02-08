package fr.univartois.ili.sadoc.metier.commun.services;

import java.util.List;

import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;

/**
 * 
 * @author Mohamed Belhadj-adda <belhadjadda.mohamed at gmail.com>
 * 
 */
public interface IMetierCommunServices {

	/**
	 * Ask the Item to the Database
	 * 
	 * @param id
	 * @return
	 */
	Item findItemById(int id);

	/**
	 * Ask the list of Item by Competence
	 * 
	 * @param competence
	 * @return
	 */
	List<Item> findItemByCompetence(Competence competence);
	
	/**
	 * Insert the Item in Database
	 * 
	 * @param item
	 */
	void createItem(Item item);
	
	/**
	 * Ask the competence to the Database
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
	 * Ask the list of Competence by domaine
	 * 
	 * @param domaine
	 * @return
	 */
	List<Competence> findCompetenceByDomaine(Domaine domaine);

	/**
	 * Ask the domaine to the Database
	 * 
	 * @param id
	 * @return 
	 */
	Domaine findDomaineById(int id);

	/**
	 * Insert the domaine in Database
	 * 
	 * @param domaine
	 */
	void createDomaine(Domaine domaine);

	/**
	 * Ask the referentiel to the Database
	 * 
	 * @param id
	 * @return
	 */
	Referentiel findReferentielById(int id);

	/**
	 * Ask the list of Domaine by Referentiel
	 * 
	 * @param referentiel
	 * @return
	 */
	List<Domaine> findDomaineByReferentiel(Referentiel referentiel);
	
	/**
	 * Insert the referentiel in Database
	 * 
	 * @param referentiel
	 */
	void createReferentiel(Referentiel referentiel);

}
