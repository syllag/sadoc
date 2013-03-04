package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Certificate;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.entities.Signature;
import fr.univartois.ili.sadoc.dao.services.ISignatureDAO;

@Repository("signatureDAO")
@Transactional
public class SignatureDAO extends AbstractWebServiceDAO implements
		ISignatureDAO {

	public SignatureDAO() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Signature signature) {
		entityManager.persist(signature);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Signature findById(long id) {
		return entityManager.find(Signature.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByOwnerWS(OwnerWS owner) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(Request.FIND_IN_SIGNATURE_BY_OWNER,
				Signature.class);
		query.setParameter("owner", owner);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByDocument(Document document) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(
				Request.FIND_IN_SIGNATURE_BY_DOCUMENT, Signature.class);
		query.setParameter("document", document);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Signature> findByCertificate(Certificate certificate) {
		final TypedQuery<Signature> query;
		query = entityManager.createQuery(
				Request.FIND_IN_SIGNATURE_BY_CERTIFICATE, Signature.class);
		query.setParameter("certificate", certificate);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Document> findDocumentByOwnerWS(OwnerWS owner) {
		final TypedQuery<Document> query;
		query = entityManager.createQuery(
				Request.FIND_DOCUMENT_IN_SIGNATURE_BY_OWNER, Document.class);
		query.setParameter("owner", owner);
		List<Document> documents = query.getResultList();
		return documents;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Signature signature) {
		entityManager.merge(signature);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Signature signature) {
		entityManager.remove(signature);
	}

	@Override
	public void refresh(Signature entity) {
		entityManager.refresh(entity);
	}
}
