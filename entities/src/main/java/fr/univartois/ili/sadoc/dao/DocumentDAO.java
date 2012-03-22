package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.univartois.ili.sadoc.entities.Document;

public class DocumentDAO {

	protected EntityManager entityManager;

	public EntityManager getentityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public DocumentDAO(){
		entityManager = PersistenceProvider.getEntityManager();

	}
	
	public void create(Document document) {
		entityManager.getTransaction().begin();
		entityManager.persist(document);
		entityManager.getTransaction().commit();
	}

	public Document findById(String id) {
		Document document = entityManager.find(Document.class, id);
		return document;
	}

	public void update(Document document) {
		entityManager.getTransaction().begin();
		entityManager.merge(document);
		entityManager.getTransaction().commit();
	}

	public void delete(Document document) {
		entityManager.getTransaction().begin();
		entityManager.remove(document);
		entityManager.getTransaction().commit();
	}
}
