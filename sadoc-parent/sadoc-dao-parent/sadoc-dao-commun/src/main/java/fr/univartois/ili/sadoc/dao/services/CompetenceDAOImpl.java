/**
 * 
 */
package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;

/**
 * @author pp
 *
 */
public class CompetenceDAOImpl implements ICompetenceDAO{
	private EntityManager em;
	
	/**
	 * Contructor
	 * 
	 * @param em
	 */
	public CompetenceDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Competence findCompetenceById(long id) {
		return em.find(Competence.class, id);
	}

	public void createCompetence(Competence competence) {
		em.getTransaction().begin();
		em.persist(competence);
		em.getTransaction().commit();
	}

	@Override
	public List<Competence> findCompetenceByDomaine(Domaine domaine) {
		TypedQuery<Competence> query = em.createQuery(
				"SELECT c FROM Competence c WHERE c.domaine = :domaine",
				Competence.class);
		query.setParameter("domaine", domaine);
		return query.getResultList();
	}

	@Override
	public Competence findByAcronym(String acronym) {
		TypedQuery<Competence> query = em.createQuery(
				"SELECT c FROM Competence c WHERE c.codeCompetence = :acronym",
				Competence.class);
		query.setParameter("acronym", acronym);
		return query.getSingleResult();
	}

}
