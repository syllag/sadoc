package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.User;

public abstract class UserDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public static void create(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public static User findById(int id) {
        User user = em.find(User.class, id);
		return user;
	}

	public static void update(User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	public static void delete(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}
}
