package fr.univartois.ili.sadoc.dao.services;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Referentiel;

@Repository("ReferentielDAO")
@Transactional
public class ReferentielDAOImpl extends AbstractCommunDAO implements IReferentielDAO {

	public ReferentielDAOImpl() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Referentiel findReferentielById(long id) {
		Referentiel referentiel = entityManager.find(Referentiel.class, id);
		return referentiel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createReferentiel(Referentiel referentiel) {		
		entityManager.persist(referentiel);		
	}	

}
