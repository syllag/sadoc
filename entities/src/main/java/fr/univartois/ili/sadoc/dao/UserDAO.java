package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.webservice.User;

public abstract class UserDAO {

	private static final String FIND_BY_ID = "SELECT user FROM User user Where user.id = ?";

	public static void create(User user) {
		EntityManager em = PersistenceProvider.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public static User findById(int id) {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<User> query = em.createQuery(FIND_BY_ID, User.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}

	public static void update(User user) {
		EntityManager em = PersistenceProvider.getEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	public static void delete(User user) {
		EntityManager em = PersistenceProvider.getEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
