package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Ignore;
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

	@Test
	public void testCreate() {
		Domaine domaine = new Domaine();
		domaine.setCodeDomaine("codeDomaine");
		domaine.setDescription("description");
		domaine.setId(1);
		domaine.setReferentiel(null);
		ddaoi.createDomaine(domaine);
		Domaine d = em.find(Domaine.class, domaine.getId());
		assertEquals(domaine, d);
	}

	@Ignore@Test
	public void testSearch() {
	}

}
