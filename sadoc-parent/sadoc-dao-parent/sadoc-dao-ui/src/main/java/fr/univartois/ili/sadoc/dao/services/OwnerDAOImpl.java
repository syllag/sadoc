package fr.univartois.ili.sadoc.dao.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.dao.entities.Owner;

public class OwnerDAOImpl extends AbstractWebAppDAO implements IOwnerDAO {

	private EntityManager em; 

	public OwnerDAOImpl() {
		// empty constructor for test
	}
	
	public OwnerDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Owner findOwnerById(long id) {
		return em.find(Owner.class,id);
	}

	@Override
	public void createOwner(Owner owner) {
			em.getTransaction().begin();
			em.persist(owner);
			em.getTransaction().commit();
	}

	@Override
	public void updateOwner(Owner owner) {
			em.getTransaction().begin();
			em.merge(owner);
			em.getTransaction().commit();
	}

	
	@Override
	//  S'assurer que le password a été crypté
	public Owner findOwnerByEmailAndPassword(String email, String password) {
		TypedQuery<Owner> query=em.createQuery("SELECT s FROM Owner s WHERE s.mail=:mail AND s.password=:password",Owner.class);
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
	public Owner findOwnerByEmail(String email) {
		TypedQuery<Owner> query=em.createQuery("SELECT s FROM Owner s WHERE s.mail=:email",Owner.class);
		query.setParameter("email",email);
		Owner res=null;
		try {
			res=query.getSingleResult();
		} catch(NoResultException e) {
		}
		return res;
	}
}
