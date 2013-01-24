package fr.univartois.ili.sadoc.entitiesws.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entitiesws.classes.Document;

@Service("documentDAO")
@Transactional
public class DocumentDAO {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public DocumentDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Document document) {
		entityManager.persist(document);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Document findById(int id) {
		Document d =entityManager.find(Document.class, id);
		return d;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Document document) {
		entityManager.merge(document);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Document document) {
		entityManager.remove(document);
	}
}
