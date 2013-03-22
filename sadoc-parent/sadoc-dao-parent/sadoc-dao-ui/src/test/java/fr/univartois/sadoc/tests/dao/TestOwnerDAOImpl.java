package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Owner;
import fr.univartois.ili.sadoc.dao.services.IOwnerDAO;

public class TestOwnerDAOImpl {
	
	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static IOwnerDAO iOwnerDAO;
	private static ApplicationContext appContext;	
	
	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		iOwnerDAO = appContext.getBean("ownerDAO",IOwnerDAO.class);
		assertNotNull(iOwnerDAO);
	}
	
	@Test
	public void testCreateOwner() {		
		Owner owner = new Owner();
		iOwnerDAO.createOwner(owner);
		assertNotNull(owner.getId());
		Owner o = iOwnerDAO.findOwnerById(owner.getId());
		assertSame(owner, o);
	}
	
	@Test
	public void TestUpdateOwner() {
		Owner owner = new Owner("toto", "titi", "toto@test.fr", "titi",
				"machin", "62300", "Lens", "0628458763");
		iOwnerDAO.createOwner(owner);
		assertNotNull(owner.getId());		
		Owner o = iOwnerDAO.findOwnerById(owner.getId());
		assertEquals(owner.getId(), o.getId());
		o.setLastName("yoyo");
		o.setFirstName("tata");
		o.setMail("tutu@test.fr");
		o.setPassword("truc");
		o.setAddress("plop");
		o.setZipCode("59000");
		o.setTown("Lille");
		o.setPhone("0606060606");
		iOwnerDAO.updateOwner(o);
		
		Owner find = iOwnerDAO.findOwnerById(owner.getId());
		assertEquals("yoyo", find.getLastName());
		assertEquals("tata",find.getFirstName());
		assertEquals("tutu@test.fr",find.getMail());
		assertEquals("truc",find.getPassword());
		assertEquals("plop",find.getAddress());
		assertEquals("Lille",find.getTown());
		assertEquals("59000",find.getZipCode());
		assertEquals("0606060606",find.getPhone());
		
		assertSame(find, o);
	}
	
	//TODO passer les tests en spring
//
//	@Test
//	public void TestFindOwnerByEmailAndPassword() {
//		Owner owner = new Owner("toto", "titi", "toto@test.fr", "passe",
//				"machin", "62300", "Lens", "0628458763");
//		owner.setId(ID_TEST);
//		em.getTransaction().begin();
//		em.persist(owner);
//		em.getTransaction().commit();
//		Owner find = impl.findOwnerByEmailAndPassword("toto@test.fr", "passe");
//		
//		assertSame(find, owner);
//		
//		em.getTransaction().begin();
//		em.remove(owner);
//		em.getTransaction().commit();
//	}
//
//	@Test
//	public void TestFindOwnerByEmail() {
//		Owner owner = new Owner("toto", "titi", "toto@test.fr", "titi",
//				"machin", "62300", "Lens", "0628458763");
//		owner.setId(ID_TEST);
//		em.getTransaction().begin();
//		em.persist(owner);
//		em.getTransaction().commit();
//		Owner find = impl.findOwnerByEmail("toto@test.fr");
//		assertSame(find, owner);
//		
//		em.getTransaction().begin();
//		em.remove(owner);
//		em.getTransaction().commit();
//	}
}
