package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Certificate;

public abstract class CertificateDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Certificate certificate) {
		em.getTransaction().begin();
		em.persist(certificate);
		em.getTransaction().commit();
	}

	public static Certificate findById(int id) {
		Certificate certificate = em.find(Certificate.class, id);
		return certificate;
	}

	public static void update(Certificate certificate) {
		em.getTransaction().begin();
		em.merge(certificate);
		em.getTransaction().commit();
	}

	public static void delete(Certificate certificate) {
		em.getTransaction().begin();
		em.remove(certificate);
		em.getTransaction().commit();
	}
}
