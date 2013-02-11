package fr.univartois.sadoc.dao.services;

import fr.univartois.sadoc.dao.entities.Competence;


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
	Competence findCompetenceById(int id);

	/**
	 * Insert the competence in DB
	 * 
	 * @param competence that is the competence to create 
	 */
	void createCompetence(Competence competence);

}
