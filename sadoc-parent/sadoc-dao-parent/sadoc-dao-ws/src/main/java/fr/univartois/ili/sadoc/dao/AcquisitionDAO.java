package fr.univartois.ili.sadoc.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Acquisition;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.services.IAcquisitionDAO;

@Repository("acquisitionDAO")
@Transactional
public class AcquisitionDAO extends AbstractWebServiceDAO implements IAcquisitionDAO {
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Acquisition entity) {
		entityManager.persist(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Acquisition findById(long id) {
		return entityManager.find(Acquisition.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Acquisition entity) {
		entityManager.remove(entity);		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Acquisition entity) {
		entityManager.merge(entity);	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void refresh(Acquisition entity) {
		entityManager.refresh(entity);	
	}

	@Override
	public List<Acquisition> findAcquisitionByDocument(Document document) {
		TypedQuery<Acquisition> querry = entityManager.createQuery(Request.FIND_ACQUISITION_BY_DOCUMENT, Acquisition.class);
		querry.setParameter("document", document);
		return querry.getResultList();
	}

	@Override
	public List<Acquisition> findAcquisitionByOwner(int owner) {
		TypedQuery<Acquisition> querry = entityManager.createQuery(Request.FIND_ACQUISITION_BY_OWNER, Acquisition.class);
		querry.setParameter("id", owner);
		return querry.getResultList();
	}
}
