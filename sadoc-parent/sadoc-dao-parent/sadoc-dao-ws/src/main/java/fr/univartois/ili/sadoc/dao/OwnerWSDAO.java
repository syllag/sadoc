package fr.univartois.ili.sadoc.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.IOwnerDAO;
import fr.univartois.ili.sadoc.dao.services.IWebServiceDAO;

@Service("ownerDAO")
@Transactional
public class OwnerWSDAO extends AbstractWebServiceDAO implements IWebServiceDAO<OwnerWS>,IOwnerDAO{

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
