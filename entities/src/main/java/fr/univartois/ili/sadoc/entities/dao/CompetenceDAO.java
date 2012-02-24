package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.configuration.Request;

public class CompetenceDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	private CompetenceDAO(){}
	
	public static void create(Competence competence) {
		em.getTransaction().begin();
		em.persist(competence);
		em.getTransaction().commit();
	}

	public static Competence findById(int id) {
        return em.find(Competence.class, id);
	}
	
	public static Competence findByAcronym(String acronym) {
		final TypedQuery<Competence> query;
		query = em.createQuery(Request.FIND_COMPETENCE_BY_ACRONYM,
				Competence.class);
		query.setParameter("acronym", acronym);
		Competence competence = query.getSingleResult();
		return competence;
	}

	public static void update(Competence competence) {
		em.getTransaction().begin();
		em.merge(competence);
		em.getTransaction().commit();
	}

	public static void delete(Competence competence) {
		em.getTransaction().begin();
		em.remove(competence);
		em.getTransaction().commit();
	}
}
