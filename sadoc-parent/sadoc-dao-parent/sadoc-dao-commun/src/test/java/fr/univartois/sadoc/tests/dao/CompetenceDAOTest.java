package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;


public class CompetenceDAOTest {

	private EntityManager em;
	
	
	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static ICompetenceDAO competenceDAO;
	private static ApplicationContext appContext;	
	private Competence competence;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		competenceDAO = appContext.getBean("competenceDAO",ICompetenceDAO.class);
		assertNotNull(competenceDAO);
	}
	
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testCreate() {
		competence = new Competence();
		competenceDAO.createCompetence(competence);
		Competence c = competenceDAO.findCompetenceById(competence.getId());
		assertEquals(competence, c);
		assertNotNull("id not null", competence.getId());
		competenceDAO.
//		em.getTransaction().begin();
//		em.remove(competence);
//		em.getTransaction().commit();
	}
//
//	@Test
//	public void testSearchId() {
//		Competence competence = new Competence();
//		em.getTransaction().begin();
//		em.persist(competence);
//		em.getTransaction().commit();
//		Competence competenceResults = cdaoi.findCompetenceById(competence
//				.getId());
//		assertSame(competence, competenceResults);
//		em.getTransaction().begin();
//		em.remove(competence);
//		em.getTransaction().commit();
//	}
//
//	@Test
//	public void testFindCompetenceByDomaine() {
//		Domaine domaine = new Domaine();
//
//		Competence competence = new Competence();
//		competence.setDomaine(domaine);
//		em.getTransaction().begin();
//		em.persist(domaine);
//		em.persist(competence);
//		em.getTransaction().commit();
//		List<Competence> competences = cdaoi.findCompetenceByDomaine(domaine);
//		assertTrue(competences.contains(competence));
//	}
//
//	@Test
//	public void testFindCompetenceByAcronym() {
//		String codeComp = "D1";
//
//		Competence competence = new Competence();
//		competence.setCodeCompetence(codeComp);
//		em.getTransaction().begin();
//		em.persist(competence);
//		em.getTransaction().commit();
//		Competence comp = cdaoi.findByAcronym(codeComp);
//		assertTrue(competence.getCodeCompetence().equals(
//				comp.getCodeCompetence()));
//	}

}
