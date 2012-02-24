package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.configuration.Request;
import fr.univartois.ili.sadoc.entities.Owner;

public class OwnerDAO {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public OwnerDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	public void create(Owner user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	public Owner findById(int id) {
		Owner user = entityManager.find(Owner.class, id);
		return user;
	}

	public Owner findByMail(String mail) {
		TypedQuery<Owner> query;
		query = entityManager.createQuery(Request.FIND_OWNER_BY_MAIL,
				Owner.class);
		query.setParameter("mail", mail);
		return query.getSingleResult();
	}

	public Owner findOwner(String mail, String password) {
		TypedQuery<Owner> query;
		query = entityManager.createQuery(Request.FIND_OWNER_BY_MAIL_PASSWORD,
				Owner.class);
		query.setParameter("mail", mail);
		query.setParameter("password", password);
		return query.getSingleResult();
	}

	public void update(Owner user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public void delete(Owner user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}
}
