package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.DomaineDAOImpl;

public class DomaineDAOTest {

	private EntityManager em;
	private DomaineDAOImpl ddaoi;

	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("sadocDAOcommun");
		em = emf.createEntityManager();
		ddaoi = new DomaineDAOImpl(em);
	}

	@After
	public void tearDown() {
		em.close();
	}

	@Test
	public void testSearchId() {
		Domaine domaine = new Domaine();
		domaine.setCodeDomaine("codeDomaine");
		domaine.setDescription("description");
		em.getTransaction().begin();
		em.persist(domaine);
		em.getTransaction().commit();
		Domaine domaineResults = ddaoi.findDomaineById(domaine.getId());
		assertSame(domaineResults, domaine);	
		em.getTransaction().begin();
		em.remove(domaine);
		em.getTransaction().commit();
	}

	@Test
	public void testCreate() {
		Domaine domaine = new Domaine();
		ddaoi.createDomaine(domaine);
		Domaine d = em.find(Domaine.class, domaine.getId());
		assertEquals(domaine, d);
		assertNotNull("id not null", domaine.getId());
		em.getTransaction().begin();
		em.remove(domaine);
		em.getTransaction().commit();
	}

	@Test
	public void testSearchReferentiel() {
		Domaine domaine = new Domaine();
		Referentiel referentiel = new Referentiel();
		em.getTransaction().begin();
		em.persist(referentiel);
		em.getTransaction().commit();
		domaine.setReferentiel(referentiel);
		em.getTransaction().begin();
		em.persist(domaine);
		em.getTransaction().commit();
		List<Domaine> domaineResults = ddaoi
				.findDomaineByReferentiel(referentiel);
		assertTrue(domaineResults.contains(domaine));

		em.getTransaction().begin();
		em.remove(referentiel);
		em.remove(domaine);
		em.getTransaction().commit();
	}
}
