package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Acquisition;

public abstract class DegreeDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	
	
	public static void create(Acquisition acquisition) {
		em.getTransaction().begin();
		em.persist(acquisition);
		em.getTransaction().commit();
	}

	public static Acquisition findById(int id) {
        Acquisition acquisition = em.find(Acquisition.class, id);
		return acquisition;
	}

	public static void update(Acquisition acquisition) {
		em.getTransaction().begin();
		em.merge(acquisition);
		em.getTransaction().commit();
	}

	public static void delete(Acquisition acquisition) {
		em.getTransaction().begin();
		em.remove(acquisition);
		em.getTransaction().commit();
	}
}
