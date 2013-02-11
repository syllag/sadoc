package fr.univartois.ili.sadoc.dao.services;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.dao.entities.Referentiel;

public class ReferentielDAOImpl implements IReferentielDAO {

	private EntityManager em;

	public ReferentielDAOImpl(EntityManager em) {
		this.setEm(em);
	}

	@Override
	public Referentiel findReferentielById(long id) {
		Referentiel referentiel = em.find(Referentiel.class, id);
		return referentiel;
	}

	@Override
	public void createReferentiel(Referentiel referentiel) {
		em.getTransaction().begin();
		em.persist(referentiel);
		em.getTransaction().commit();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
