package fr.univartois.ili.sadoc.entities.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.configuration.Request;

public class CertificateDAO {

	private static final EntityManager em = PersistenceProvider
			.getEntityManager();

	public CertificateDAO(){}
	
	public static void create(Certificate certificate) {
		em.getTransaction().begin();
		em.persist(certificate);
		em.getTransaction().commit();
	}

	public static Certificate findById(int id) {
		Certificate certificate = em.find(Certificate.class, id);
		return certificate;
	}

	public static List<Certificate> findByOwner(Owner owner) {
		final TypedQuery<Certificate> query;
		query = em.createQuery(Request.FIND_IN_CERTIFICATE_BY_OWNER,
				Certificate.class);
		query.setParameter("owner", owner);
		List<Certificate> certificates = query.getResultList();
		return certificates;
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
