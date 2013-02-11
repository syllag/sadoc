package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Item;
import fr.univartois.ili.sadoc.dao.services.ItemDAOImpl;

public class ItemDAOTest {

	private EntityManager em;
	private ItemDAOImpl idaoi;

	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("sadocDAOcommun");
		em = emf.createEntityManager();
		idaoi = new ItemDAOImpl(em);
	}
	
	@After
	public void tearDown() {
		if (em != null) {
			em.close();
		}
	}

	@Test
	public void testCreate() {
		Item item = new Item();
		item.setCodeItem("code item");
		item.setDescription("description");
		item.setId(1);
		item.setCompetence(null);
		idaoi.createItem(item);
		Item i = em.find(Item.class, item.getId());
		assertEquals(item, i);
	}

	@Test
	public void testFindItemById() {
		Item item = new Item();
		item.setCodeItem("code item");
		item.setDescription("description");
		item.setId(2);
		item.setCompetence(null);
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		Item i = idaoi.findItemById(item.getId());
		assertSame(item, i);	
	}
	
	@Test
	public void testFindItemByCompetence() {
		Competence comp = new Competence();
		comp.setCodeCompetence("code item");
		comp.setDescription("description");
		comp.setId(3);
		Item item1 = new Item();
		item1.setCodeItem("code item");
		item1.setDescription("description");
		item1.setId(3);
		item1.setCompetence(comp);
		em.getTransaction().begin();
		em.persist(comp);
		em.persist(item1);
		em.getTransaction().commit();
		List<Item> items = idaoi.findItemByCompetence(comp);
		assertTrue(items.contains(item1));
	}


}
