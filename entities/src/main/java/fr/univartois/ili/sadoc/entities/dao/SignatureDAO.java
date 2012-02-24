package fr.univartois.ili.sadoc.entities.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.configuration.Request;

@Service("signatureDAO")
@Transactional
public class SignatureDAO {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public SignatureDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Signature signature) {
		entityManager.persist(signature);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Signature findById(int id) {
		return entityManager.find(Signature.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByOwner(Owner owner) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(Request.FIND_IN_SIGNATURE_BY_OWNER,
				Signature.class);
		query.setParameter("owner", owner);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByDocument(Document document) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(
				Request.FIND_IN_SIGNATURE_BY_DOCUMENT, Signature.class);
		query.setParameter("owner", document);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByCompetence(Competence competence) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(
				Request.FIND_IN_SIGNATURE_BY_COMPETENCE, Signature.class);
		query.setParameter("owner", competence);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByCertificate(Certificate certificate) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(
				Request.FIND_IN_SIGNATURE_BY_CERTIFICATE, Signature.class);
		query.setParameter("certificate", certificate);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Document> findDocumentByOwner(Owner owner) {
		final TypedQuery<Document> query;
		query = entityManager.createQuery(
				Request.FIND_DOCUMENT_IN_SIGNATURE_BY_OWNER, Document.class);
		query.setParameter("owner", owner);
		List<Document> documents = query.getResultList();
		Set<Document> docs = new HashSet<Document>(documents);
		documents = new ArrayList<Document>(docs);
		Collections.sort(documents);
		return documents;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findOwnerByDocument(Document document) {
		final TypedQuery<Owner> query;
		query = entityManager.createQuery(
				Request.FIND_OWNER_IN_SIGNATURE_BY_DOCUMENT, Owner.class);
		query.setParameter("document", document);
		return query.getSingleResult();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Competence> findCompetenceByDocument(Document document) {
		final TypedQuery<Competence> query;
		query = entityManager.createQuery(
				Request.FIND_COMPETENCE_IN_SIGNATURE_BY_DOCUMENT,
				Competence.class);
		query.setParameter("document", document);
		List<Competence> competences = query.getResultList();
		Set<Competence> docs = new HashSet<Competence>(competences);
		competences = new ArrayList<Competence>(docs);
		Collections.sort(competences);
		return competences;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Signature signature) {
		entityManager.merge(signature);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Signature signature) {
		entityManager.remove(signature);
	}
}
