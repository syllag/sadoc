package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Resume;

public abstract class ResumeDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Resume resume) {
		em.getTransaction().begin();
		em.persist(resume);
		em.getTransaction().commit();
	}

	public static Resume findById(int id) {
		Resume resume = em.find(Resume.class, id);
		return resume;
	}

	public static void update(Resume resume) {
		em.getTransaction().begin();
		em.merge(resume);
		em.getTransaction().commit();
	}

	public static void delete(Resume resume) {
		em.getTransaction().begin();
		em.remove(resume);
		em.getTransaction().commit();
	}
}
