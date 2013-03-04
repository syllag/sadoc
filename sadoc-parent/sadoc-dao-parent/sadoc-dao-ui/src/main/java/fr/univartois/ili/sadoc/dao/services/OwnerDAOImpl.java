package fr.univartois.ili.sadoc.dao.services;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Owner;

@Service("certificateDAO")
@Transactional
public class OwnerDAOImpl extends AbstractWebAppDAO implements IOwnerDAO {

	public OwnerDAOImpl() {
		super();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findOwnerById(long id) {
		return entityManager.find(Owner.class,id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createOwner(Owner owner) {
		entityManager.persist(owner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateOwner(Owner owner) {
		entityManager.merge(owner);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findOwnerByEmailAndPassword(String email, String password) {
		TypedQuery<Owner> query=entityManager.createQuery("SELECT s FROM Owner s WHERE s.mail=:mail AND s.password=:password",Owner.class);
		query.setParameter("mail",email);
		query.setParameter("password",password);
		Owner res=null;
		try {
			res=query.getSingleResult();
		} catch(NoResultException e) {
		}
		return res;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findOwnerByEmail(String email) {
		TypedQuery<Owner> query=entityManager.createQuery("SELECT s FROM Owner s WHERE s.mail=:email",Owner.class);
		query.setParameter("email",email);
		Owner res=null;
		try {
			res=query.getSingleResult();
		} catch(NoResultException e) {
		}
		return res;
	}
}
