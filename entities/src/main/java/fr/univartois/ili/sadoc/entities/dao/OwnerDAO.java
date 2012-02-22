package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.configuration.Request;

@Service("ownerDAO")
@Transactional
public class OwnerDAO {

	private static EntityManager em;

	public OwnerDAO() {
		// em= PersistenceProvider.getEntityManager();
	}

	public void create(Owner owner) {

		System.out
				.println("la création de Owner ça marche: " + owner.getMail());

		// em.persist(owner);

	}

	public Owner findById(int id) {
		Owner user = em.find(Owner.class, id);
		return user;
	}

	public Owner findByMail(String mail) {
		final TypedQuery<Owner> query;
		query = em.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		Owner owner = query.getSingleResult();
		return owner;
	}

	public void update(Owner user) {
		em.merge(user);
	}

	public void delete(Owner user) {

		em.remove(user);

	}
}
