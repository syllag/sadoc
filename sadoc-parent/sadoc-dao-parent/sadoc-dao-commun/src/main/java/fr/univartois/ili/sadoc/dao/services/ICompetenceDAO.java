package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;


/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface ICompetenceDAO {

	/**
	 * Ask the competence identified by id of the DB
	 * 
	 * @param id
	 * @return Competence
	 * return the corresponding competence
	 */
	Competence findCompetenceById(long id);


	
	/**
	 * Ask the list of Competence by domaine
	 * 
	 * @param domaine
	 * @return List<Competence>
	 */
	public abstract List<Competence> findCompetenceByDomaine(Domaine domaine);

	public abstract Competence findByAcronym(String acronym);
	
	/**
	 * Insert the competence in DB
	 * 
	 * @param competence that is the competence to create 
	 */
	
	void createCompetence(Competence competence);
	
	public void removeCompetence(Competence competence);

}
