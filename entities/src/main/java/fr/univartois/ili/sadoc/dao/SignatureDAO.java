package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Signature;

public abstract class SignatureDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Signature signature) {
		em.getTransaction().begin();
		em.persist(signature);
		em.getTransaction().commit();
	}

	public static Signature findById(int id) {
		Signature signature = em.find(Signature.class, id);
		return signature;
	}
	
	public static List<Signature> findByOwner(Owner owner) {
		final TypedQuery<Signature> query;
        query = em.createQuery(Request.FIND_IN_SIGNATURE_BY_OWNER, Signature.class);
        query.setParameter("owner", owner);
        List<Signature> signature = query.getResultList();
		return signature;
	}
	
	public static List<Signature> findByDocument(Document document) {
		final TypedQuery<Signature> query;
        query = em.createQuery(Request.FIND_IN_SIGNATURE_BY_DOCUMENT, Signature.class);
        query.setParameter("owner", document);
        List<Signature> signature = query.getResultList();
		return signature;
	}
	
	public static List<Signature> findByCompetence(Competence competence) {
		final TypedQuery<Signature> query;
        query = em.createQuery(Request.FIND_IN_SIGNATURE_BY_COMPETENCE, Signature.class);
        query.setParameter("owner", competence);
        List<Signature> signature = query.getResultList();
		return signature;
	}
	
	public static List<Signature> findByCertificate(Certificate certificate) {
		final TypedQuery<Signature> query;
        query = em.createQuery(Request.FIND_IN_SIGNATURE_BY_COMPETENCE, Signature.class);
        query.setParameter("owner", certificate);
        List<Signature> signature = query.getResultList();
		return signature;
	}

	public static void update(Signature signature) {
		em.getTransaction().begin();
		em.merge(signature);
		em.getTransaction().commit();
	}

	public static void delete(Signature signature) {
		em.getTransaction().begin();
		em.remove(signature);
		em.getTransaction().commit();
	}
}
