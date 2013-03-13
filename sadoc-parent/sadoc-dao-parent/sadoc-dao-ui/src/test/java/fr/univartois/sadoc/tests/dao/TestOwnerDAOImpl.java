package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.services.OwnerDAOImpl;

public class TestOwnerDAOImpl {
	//TODO passer les tests en spring
//	private static final String PERSISTANCE_UNIT_NAME = "sadocDAOui";
//	private static final long ID_TEST = 1;
//	private EntityManager em;
//	private OwnerDAOImpl impl;
//
//	@Before
//	public void startUp() {
//		EntityManagerFactory emf = Persistence
//				.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
//		em = emf.createEntityManager();
//		impl = new OwnerDAOImpl(em);
//
//	}
//
//	@After
//	public void shutDown() {
//		if (em != null)
//			em.close();
//	}
//
//	@Test
//	public void TestCreateOwner() {
//		long nbowner = em.createQuery("SELECT COUNT(o) FROM Owner o",
//				Long.class).getSingleResult();
//		assertEquals(nbowner, 0);
//		Owner owner = new Owner("toto", "titi", "toto@test.fr", "titi",
//				"machin", "62300", "Lens", "0628458763");
//		owner.setId(ID_TEST);
//		impl.createOwner(owner);
//		
//	nbowner = em.createQuery("SELECT COUNT(o) FROM Owner o", Long.class)
//			.getSingleResult();
//		assertEquals(nbowner, 1);
//	Owner verif= em.createQuery("SELECT o FROM Owner o",Owner.class).getSingleResult();
//		assertSame(owner,verif);
//		
//		em.getTransaction().begin();
//		em.remove(owner);
//		em.getTransaction().commit();
//	}
//
//	@Test
//	public void TestUpdateOwner() {
//		Owner owner = new Owner("toto", "titi", "toto@test.fr", "titi",
//				"machin", "62300", "Lens", "0628458763");
//		owner.setId(ID_TEST);
//		em.getTransaction().begin();
//		em.persist(owner);
//		em.getTransaction().commit();
//		long nbowner = em.createQuery("SELECT COUNT(o) FROM Owner o",
//				Long.class).getSingleResult();
//		assertEquals(nbowner, 1);
//		Owner modify = impl.findOwnerById(ID_TEST);
//		modify.setLastName("yoyo");
//		modify.setFirstName("tata");
//		modify.setMail("tutu@test.fr");
//		modify.setPassword("truc");
//		modify.setAddress("plop");
//		modify.setZipCode("59000");
//		modify.setTown("Lille");
//		modify.setPhone("0606060606");
//		impl.updateOwner(modify);
//		
//		nbowner = em.createQuery("SELECT COUNT(o) FROM Owner o", Long.class)
//				.getSingleResult();
//		assertEquals(nbowner, 1);
//		Owner find = impl.findOwnerById(ID_TEST);
//		assertEquals("yoyo", find.getLastName());
//		assertEquals("tata",find.getFirstName());
//		assertEquals("tutu@test.fr",find.getMail());
//		assertEquals("truc",find.getPassword());
//		assertEquals("plop",find.getAddress());
//		assertEquals("Lille",find.getTown());
//		assertEquals("59000",find.getZipCode());
//		assertEquals("0606060606",find.getPhone());
//		
//		em.getTransaction().begin();
//		em.remove(owner);
//		em.getTransaction().commit();
//	}
//
//	@Test
//	public void TestFindOwnerById() {
//		Owner owner = new Owner("toto", "titi", "toto@test.fr", "titi",
//				"machin", "62300", "Lens", "0628458763");
//		owner.setId(ID_TEST);
//		em.getTransaction().begin();
//		em.persist(owner);
//		em.getTransaction().commit();
//		Owner find = impl.findOwnerById(ID_TEST);
//		assertEquals(ID_TEST, find.getId());
//		assertSame(owner,find);
//		
//		em.getTransaction().begin();
//		em.remove(owner);
//		em.getTransaction().commit();
//	}
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
