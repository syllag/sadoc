package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractWebServiceDAO {

	protected EntityManager entityManager;

	public AbstractWebServiceDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}