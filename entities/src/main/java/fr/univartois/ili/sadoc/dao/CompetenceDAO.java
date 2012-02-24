package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Competence;

public class CompetenceDAO {

	protected EntityManager entityManager;

	public EntityManager getentityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public CompetenceDAO(){
		entityManager = PersistenceProvider.getEntityManager();
	}
	
	public void create(Competence competence) {
		entityManager.getTransaction().begin();
		entityManager.persist(competence);
		entityManager.getTransaction().commit();
	}

	public Competence findById(int id) {
		Competence competence = entityManager.find(Competence.class, id);
		return competence;
	}

	public Competence findByAcronym(String acronym) {
		final TypedQuery<Competence> query;
		query = entityManager.createQuery(Request.FIND_COMPETENCE_BY_ACRONYM,
				Competence.class);
		query.setParameter("acronym", acronym);
		Competence competence = query.getSingleResult();
		return competence;
	}

	public void update(Competence competence) {
		entityManager.getTransaction().begin();
		entityManager.merge(competence);
		entityManager.getTransaction().commit();
	}

	public void delete(Competence competence) {
		entityManager.getTransaction().begin();
		entityManager.remove(competence);
		entityManager.getTransaction().commit();
	}
}
