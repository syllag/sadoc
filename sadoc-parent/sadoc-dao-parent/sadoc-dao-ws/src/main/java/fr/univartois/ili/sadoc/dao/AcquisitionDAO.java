package fr.univartois.ili.sadoc.dao;


import java.util.List;

import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Acquisition;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.services.IAcquisitionDAO;
import fr.univartois.ili.sadoc.dao.services.IWebServiceDAO;

public class AcquisitionDAO extends AbstractWebServiceDAO implements IWebServiceDAO<Acquisition>,IAcquisitionDAO {

	
	@Override
	public void create(Acquisition entity) {
		entityManager.persist(entity);
	}

	@Override
	public Acquisition findById(long id) {
		return entityManager.find(Acquisition.class, id);
	}

	@Override
	public void delete(Acquisition entity) {
		entityManager.remove(entity);		
	}

	@Override
	public void update(Acquisition entity) {
		entityManager.merge(entity);	
	}

	@Override
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
