package fr.univartois.ili.sadoc.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.IDocumentDAO;

@Repository("documentDAO")
@Transactional
public class DocumentDAO extends AbstractWebServiceDAO implements IDocumentDAO {

	public DocumentDAO() {
		super();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Document document) {
		entityManager.persist(document);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Document findById(long id) {
		Document d =entityManager.find(Document.class, id);
		return d;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Document findByOwnerWS(OwnerWS ownerWS) {
		// TODO Imp Method
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Document document) {
		entityManager.merge(document);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Document document) {
		entityManager.remove(document);
	}

	@Override
	public void refresh(Document entity) {
		entityManager.refresh(entity);
	}
}
