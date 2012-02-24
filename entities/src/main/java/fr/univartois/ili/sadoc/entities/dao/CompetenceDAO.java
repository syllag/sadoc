package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.configuration.Request;

public class CompetenceDAO {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CompetenceDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Competence competence) {
		entityManager.persist(competence);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Competence findById(int id) {
		return entityManager.find(Competence.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Competence findByAcronym(String acronym) {
		final TypedQuery<Competence> query;
		query = entityManager.createQuery(Request.FIND_COMPETENCE_BY_ACRONYM,
				Competence.class);
		query.setParameter("acronym", acronym);
		Competence competence = query.getSingleResult();
		return competence;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Competence competence) {
		entityManager.merge(competence);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Competence competence) {
		entityManager.remove(competence);
	}
}
