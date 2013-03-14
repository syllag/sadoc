package fr.univartois.ili.sadoc.dao.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractCommunDAO {
	
	@PersistenceContext(unitName = "sadocDAOcommun")
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
