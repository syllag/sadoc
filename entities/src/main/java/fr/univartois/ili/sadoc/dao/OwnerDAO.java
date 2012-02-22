package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Owner;

public abstract class OwnerDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(Owner user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public static Owner findById(int id) {
        Owner user = em.find(Owner.class, id);
		return user;
	}

	public static Owner findByMail(String mail) {
		TypedQuery<Owner> query;
		query = em.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		return query.getSingleResult();
	}
	
	
	public static Owner findOwner(String mail,String password) {
		TypedQuery<Owner> query;
		query = em.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		query.setParameter("password", password);
		return query.getSingleResult();
	}
	
	public static void update(Owner user) {
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	public static void delete(Owner user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
