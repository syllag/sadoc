package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Competence;

public abstract class CompetenceDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Competence competence) {
		em.getTransaction().begin();
		em.persist(competence);
		em.getTransaction().commit();
	}

	public static Competence findById(int id) {
        Competence competence = em.find(Competence.class, id);
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
