package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public abstract class AcquisitionDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Acquisition acquisition) {
		em.getTransaction().begin();
		em.persist(acquisition);
		em.getTransaction().commit();
	}

	public static Acquisition findById(int id) {
		Acquisition acquisition = em.find(Acquisition.class, id);
		return acquisition;
	}
	
	public static List<Acquisition> findByOwner(Owner owner) {
		final TypedQuery<Acquisition> query;
        query = em.createQuery(Request.FIND_IN_ACQUISITION_BY_OWNER, Acquisition.class);
        query.setParameter("owner", owner);
        List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}
	
	public static List<Acquisition> findByDocument(Document document) {
		final TypedQuery<Acquisition> query;
        query = em.createQuery(Request.FIND_IN_ACQUISITION_BY_DOCUMENT, Acquisition.class);
        query.setParameter("document", document);
        List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}
	
	public static List<Acquisition> findByCompetence(Competence competence) {
		final TypedQuery<Acquisition> query;
        query = em.createQuery(Request.FIND_IN_ACQUISITION_BY_COMPETENCE, Acquisition.class);
        query.setParameter("competence", competence);
        List<Acquisition> acquisition = query.getResultList();
		return acquisition;
	}

	public static void update(Acquisition acquisition) {
		em.getTransaction().begin();
		em.merge(acquisition);
		em.getTransaction().commit();
	}

	public static void delete(Acquisition acquisition) {
		em.getTransaction().begin();
		em.remove(acquisition);
		em.getTransaction().commit();
	}
}
