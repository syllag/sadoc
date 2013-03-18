package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Item;

@Repository("itemDAO")
@Transactional
public class ItemDAOImpl extends AbstractCommunDAO implements IItemDAO {	

	public ItemDAOImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Item findItemById(long id) {
		Item degree = entityManager.find(Item.class, id);
		return degree;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createItem(Item item) {		
		entityManager.persist(item);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Item> findItemByCompetence(Competence competence) {
		TypedQuery<Item> query = entityManager.createQuery(
				"SELECT i FROM Item i WHERE i.competence = :competence",
				Item.class);
		query.setParameter("competence", competence);
		return query.getResultList();
	}

}
