package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.univartois.ili.sadoc.entities.Degree;

public class DegreeDAO {

	protected EntityManager entityManager;

	public EntityManager getentityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public DegreeDAO(){
		entityManager = PersistenceProvider.getEntityManager();

	}
	
	public  void create(Degree degree) {
		entityManager.getTransaction().begin();
		entityManager.persist(degree);
		entityManager.getTransaction().commit();
	}

	public  Degree findById(int id) {
		Degree degree = entityManager.find(Degree.class, id);
		return degree;
	}

	public  void update(Degree degree) {
		entityManager.getTransaction().begin();
		entityManager.merge(degree);
		entityManager.getTransaction().commit();
	}

	public  void delete(Degree degree) {
		entityManager.getTransaction().begin();
		entityManager.remove(degree);
		entityManager.getTransaction().commit();
	}
}
