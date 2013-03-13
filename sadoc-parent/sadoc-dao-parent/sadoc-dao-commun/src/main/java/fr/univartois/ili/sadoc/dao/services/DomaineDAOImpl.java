package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;

@Repository("DomaineDAO")
@Transactional
public class DomaineDAOImpl extends AbstractCommunDAO implements IDomaineDAO {


	public DomaineDAOImpl() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Domaine findDomaineById(long id) {
		Domaine domaine = entityManager.find(Domaine.class, id);
		return domaine;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createDomaine(Domaine domaine) {
		entityManager.persist(domaine);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Domaine> findDomaineByReferentiel(Referentiel referentiel) {		
		TypedQuery<Domaine> query = entityManager.createQuery(
				"SELECT d FROM Domaine d WHERE d.referentiel = :referentiel",
				Domaine.class);
		query.setParameter("referentiel", referentiel);
		return query.getResultList();
	}

}
