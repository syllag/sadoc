package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Item;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;

public class ItemDAOTest {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static ICompetenceDAO competenceDAO;
	private static IItemDAO itemDAO;
	private static ApplicationContext appContext;	
	private Item item;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		competenceDAO = appContext.getBean("competenceDAO",ICompetenceDAO.class);
		itemDAO = appContext.getBean("itemDAO",IItemDAO.class);
		assertNotNull(competenceDAO);
	}
	

	@Test
	public void testCreate() {
		Item item = new Item();
		item.setCodeItem("code item");
		item.setDescription("description");
		item.setId(1);
		item.setCompetence(null);
		itemDAO.createItem(item);
		Item i = itemDAO.findItemById(item.getId());
		assertEquals(item, i);
	}

	@Test
	public void testFindItemById() {
		Item item = new Item();
		item.setCodeItem("code item");
		item.setDescription("description");
		item.setId(2);
		item.setCompetence(null);
		itemDAO.createItem(item);
		Item i = itemDAO.findItemById(item.getId());
		assertEquals(item, i);	
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
		competenceDAO.createCompetence(comp);
		itemDAO.createItem(item1);
		List<Item> items = itemDAO.findItemByCompetence(comp);
		assertTrue(items.contains(item1));
	}


}
