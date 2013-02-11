package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Item;

public class ItemDAOImpl implements IItemDAO {

	private EntityManager em;

	public ItemDAOImpl(EntityManager em) {
		this.setEm(em);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item findItemById(long id) {
		Item degree = em.find(Item.class, id);
		return degree;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createItem(Item item) {
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Item> findItemByCompetence(Competence competence) {
		TypedQuery<Item> query = em.createQuery(
				"SELECT i FROM Item i WHERE i.competence = :competence",
				Item.class);
		query.setParameter("competence", competence);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
