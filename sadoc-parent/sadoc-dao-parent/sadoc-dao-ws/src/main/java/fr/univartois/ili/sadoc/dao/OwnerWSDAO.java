package fr.univartois.ili.sadoc.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Document;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.IOwnerWSDAO;

@Repository("ownerDAO")
@Transactional()
public class OwnerWSDAO extends AbstractWebServiceDAO implements IOwnerWSDAO{

	public OwnerWSDAO() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(OwnerWS owner) {
		entityManager.persist(owner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OwnerWS findById(long id) {
		return entityManager.find(OwnerWS.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OwnerWS findByMail(String mail) {
		try {
			final TypedQuery<OwnerWS> query;
			query = entityManager.createQuery(Request.FIND_OWNER_BY_MAIL,
					OwnerWS.class);
			query.setParameter("mail", mail);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OwnerWS findByDocument(Document document) {
		final TypedQuery<OwnerWS> query = entityManager.createQuery(
				Request.FIND_OWNER_IN_SIGNATURE_BY_DOCUMENT, OwnerWS.class);
		query.setParameter("document", document);
		
		return query.getSingleResult();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(OwnerWS user) {
		entityManager.merge(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(OwnerWS user) {
		entityManager.remove(user);
	}

	@Override
	public void refresh(OwnerWS entity) {
		entityManager.refresh(entity);		
	}
}
