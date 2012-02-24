package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class AcquisitionDAO {

	protected EntityManager entityManager;

	public EntityManager getentityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	 public AcquisitionDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	public void create(Acquisition acquisition) {
		entityManager.getTransaction().begin();
		entityManager.persist(acquisition);
		entityManager.getTransaction().commit();
	}

	public Acquisition findById(int id) {
		Acquisition acquisition = entityManager.find(Acquisition.class, id);
		return acquisition;
	}

	public List<Acquisition> findByOwner(Owner owner) {
		final TypedQuery<Acquisition> query;
		query = entityManager.createQuery(Request.FIND_IN_ACQUISITION_BY_OWNER,
				Acquisition.class);
		query.setParameter("owner", owner);
		List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}

	public List<Acquisition> findByDocument(Document document) {
		final TypedQuery<Acquisition> query;
		query = entityManager.createQuery(Request.FIND_IN_ACQUISITION_BY_DOCUMENT,
				Acquisition.class);
		query.setParameter("document", document);
		List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}

	public List<Acquisition> findByCompetence(Competence competence) {
		final TypedQuery<Acquisition> query;
		query = entityManager.createQuery(Request.FIND_IN_ACQUISITION_BY_COMPETENCE,
				Acquisition.class);
		query.setParameter("competence", competence);
		List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}

	public void update(Acquisition acquisition) {
		entityManager.getTransaction().begin();
		entityManager.merge(acquisition);
		entityManager.getTransaction().commit();
	}

	public void delete(Acquisition acquisition) {
		entityManager.getTransaction().begin();
		entityManager.remove(acquisition);
		entityManager.getTransaction().commit();
	}
}
