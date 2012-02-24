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

		
		//On ne laisse pas les s.o.p. lorsque l'on commit !!!!!!!!!
		
		//System.out.println("la création de Owner ça marche: " + owner.getMail());

		// em.persist(owner);

	}

	public Owner findById(int id) {
		return  em.find(Owner.class, id);
	}

	public Owner findByMail(String mail) {
		final TypedQuery<Owner> query;
		query = em.createQuery(Request.FIND_OWNER_BY_MAIL, Owner.class);
		query.setParameter("mail", mail);
		return query.getSingleResult(); 
	}

	public void update(Owner user) {
		em.merge(user);
	}

	public void delete(Owner user) {

		em.remove(user);

	}
}
