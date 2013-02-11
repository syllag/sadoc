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

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.services.CompetenceDAOImpl;

public class CompetenceDAOTest {

	private EntityManager em;
	private CompetenceDAOImpl cdaoi;

	@Before
	public void init() {
		EntityManagerFactory fact = Persistence
				.createEntityManagerFactory("sadocDAOcommun");
		em = fact.createEntityManager();
		cdaoi = new CompetenceDAOImpl(em);
	}

	@After
	public void tearDown() {
		em.close();
	}

	@Test
	public void testCreate() {
		Competence competence = new Competence();
		cdaoi.createCompetence(competence);
		Competence c = em.find(Competence.class, competence.getId());
		assertEquals(competence, c);
		assertNotNull("id not null", competence.getId());
		em.getTransaction().begin();
		em.remove(competence);
		em.getTransaction().commit();
	}

	@Test
	public void testSearchId() {
		Competence competence = new Competence();
		em.getTransaction().begin();
		em.persist(competence);
		em.getTransaction().commit();
		Competence competenceResults = cdaoi.findCompetenceById(competence
				.getId());
		assertSame(competence, competenceResults);
		em.getTransaction().begin();
		em.remove(competence);
		em.getTransaction().commit();
	}

	@Test
	public void testFindCompetenceByDomaine() {
		Domaine domaine = new Domaine();

		Competence competence = new Competence();
		competence.setDomaine(domaine);
		em.getTransaction().begin();
		em.persist(domaine);
		em.persist(competence);
		em.getTransaction().commit();
		List<Competence> competences = cdaoi
				.findCompetenceByDomaine(domaine);
		assertTrue(competences.contains(competence));
	}

}
