package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.univartois.ili.sadoc.entities.Resume;

public class ResumeDAO {

	protected EntityManager entityManager;

	public EntityManager getentityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public ResumeDAO(){
		entityManager = PersistenceProvider.getEntityManager();

	}
	
	public void create(Resume resume) {
		entityManager.getTransaction().begin();
		entityManager.persist(resume);
		entityManager.getTransaction().commit();
	}

	public Resume findById(int id) {
		Resume resume = entityManager.find(Resume.class, id);
		return resume;
	}

	public void update(Resume resume) {
		entityManager.getTransaction().begin();
		entityManager.merge(resume);
		entityManager.getTransaction().commit();
	}

	public void delete(Resume resume) {
		entityManager.getTransaction().begin();
		entityManager.remove(resume);
		entityManager.getTransaction().commit();
	}
}
