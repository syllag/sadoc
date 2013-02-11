package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;

public class DomaineDAOImpl implements IDomaineDAO {

	private EntityManager em;

	public DomaineDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Domaine findDomaineById(long id) {
		Domaine domaine = em.find(Domaine.class, id);
		return domaine;
	}

	@Override
	public void createDomaine(Domaine domaine) {
		em.getTransaction().begin();
		em.persist(domaine);
		em.getTransaction().commit();
	}

	@Override
	public List<Domaine> findDomaineByReferentiel(Referentiel referentiel) {		
		TypedQuery<Domaine> query = em.createQuery(
				"SELECT d FROM Domaine d WHERE d.referentiel = :referentiel",
				Domaine.class);
		query.setParameter("referentiel", referentiel);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
