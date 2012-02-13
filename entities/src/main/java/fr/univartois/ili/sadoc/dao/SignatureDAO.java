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
        query = em.createQuery(Request.FIND_IN_SIGNATURE_BY_CERTIFICATE, Signature.class);
        query.setParameter("owner", certificate);
        List<Signature> signature = query.getResultList();
		return signature;
	}
	
	public static List<Document> findDocumentByOwner(Owner owner) {
		final TypedQuery<Document> query;
        query = em.createQuery(Request.FIND_DOCUMENT_IN_SIGNATURE_BY_OWNER, Document.class);
        query.setParameter("owner", owner);
        List<Document> documents = query.getResultList();
		return documents;
	}
	
	public static Owner findOwnerByDocument(Document document) {
		final TypedQuery<Owner> query;
        query = em.createQuery(Request.FIND_OWNER_IN_SIGNATURE_BY_DOCUMENT, Owner.class);
        query.setParameter("document", document);
        Owner owner = query.getSingleResult();
		return owner;
	}
	
	public static List<Competence> findCompetenceByDocument(Document document) {
		final TypedQuery<Competence> query;
        query = em.createQuery(Request.FIND_COMPETENCE_IN_SIGNATURE_BY_DOCUMENT, Competence.class);
        query.setParameter("document", document);
        List<Competence> competences = query.getResultList();
		return competences;
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
