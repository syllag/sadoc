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
	 * Ask the list of Competence by domaine
	 * 
	 * @param domaine
	 * @return List<Competence>
	 */
	public abstract List<Competence> findCompetenceByDomaine(Domaine domaine);

	public abstract Competence findByAcronym(String acronym);

}
