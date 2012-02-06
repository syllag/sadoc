package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Degree;

public abstract class DegreeDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	
	
	public static void create(Degree degree) {
		em.getTransaction().begin();
		em.persist(degree);
		em.getTransaction().commit();
	}

	public static Degree findById(int id) {
		Degree degree = em.find(Degree.class, id);
		return degree;
	}

	public static void update(Degree degree) {
		em.getTransaction().begin();
		em.merge(degree);
		em.getTransaction().commit();
	}

	public static void delete(Degree degree) {
		em.getTransaction().begin();
		em.remove(degree);
		em.getTransaction().commit();
	}
}
