package fr.univartois.ili.sadoc.entities.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.configuration.Request;

public class OwnerDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public OwnerDAO(){}
	
	public static void create(Owner owner) {
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();
	}

	public static Owner findById(int id) {
        Owner user = em.find(Owner.class, id);
		return user;
	}
	
	public static Owner findByMail(String mail) {
		final TypedQuery<Owner> query;
		query = em.createQuery(Request.FIND_OWNER_BY_MAIL,
				Owner.class);
		query.setParameter("mail", mail);
		Owner owner = query.getSingleResult();
		return owner;
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
