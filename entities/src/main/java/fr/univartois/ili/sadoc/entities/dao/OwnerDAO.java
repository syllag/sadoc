package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.configuration.Request;

@Service("ownerDAO")
@Transactional
public class OwnerDAO {
	
	
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName="sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public OwnerDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Owner owner) {
		entityManager.persist(owner);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findById(int id) {
		return entityManager.find(Owner.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Owner findByMail(String mail) {
		final TypedQuery<Owner> query;
		query = entityManager.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		return query.getSingleResult(); 
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Owner user) {
		entityManager.merge(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Owner user) {
		entityManager.remove(user);
	}
}
