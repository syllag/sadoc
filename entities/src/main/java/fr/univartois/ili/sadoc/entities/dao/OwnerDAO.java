package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.configuration.Request;

@Service("ownerDAO")
@Transactional
public class OwnerDAO {
	
	protected EntityManager entityManager;

	public EntityManager getEm() {
		return entityManager;
	}
	
	@PersistenceContext(unitName="sadocjpa")
	public void setEm(EntityManager em) {
		this.entityManager = em;
	}

	public OwnerDAO() {
		 entityManager= PersistenceProvider.getEntityManager();
	}

	public void create(Owner owner) {
		System.out.println();
		System.out.println("la création de Owner ça marche: " + owner.getMail());
		System.out.println();
		entityManager.persist(owner);
		System.out.println();
		System.out.println("la persistence a été effectuée : ");
		System.out.println();

	}

	public Owner findById(int id) {
		Owner user = entityManager.find(Owner.class, id);
		return user;
	}

	public Owner findByMail(String mail) {
		final TypedQuery<Owner> query;
		query = entityManager.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		Owner owner = query.getSingleResult();
		return owner;
	}

	public void update(Owner user) {
		entityManager.merge(user);
	}

	public void delete(Owner user) {

		entityManager.remove(user);

	}
}
