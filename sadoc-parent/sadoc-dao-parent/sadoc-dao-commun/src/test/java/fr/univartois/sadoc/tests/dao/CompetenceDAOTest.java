package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;


public class CompetenceDAOTest {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static ICompetenceDAO competenceDAO;
	private static IDomaineDAO domaineDAO;
	private static ApplicationContext appContext;	
	private Competence competence;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		competenceDAO = appContext.getBean("competenceDAO",ICompetenceDAO.class);
		domaineDAO = appContext.getBean("domaineDAO",IDomaineDAO.class);
		assertNotNull(competenceDAO);
	}
	
	
	@Test
	public void testCreate() {		
		competence = new Competence();
		competenceDAO.createCompetence(competence);
		Competence c = competenceDAO.findCompetenceById(competence.getId());
		assertEquals(competence, c);
		assertNotNull("id not null", competence.getId());
		competenceDAO.removeCompetence(competence);
	}

	@Test
	public void testSearchId() {
		Competence competence = new Competence();
		competenceDAO.createCompetence(competence);
		Competence competenceResults = competenceDAO.findCompetenceById(competence
				.getId());		
		assertEquals(competence, competenceResults);
		competenceDAO.removeCompetence(competence);
	}

	@Test
	public void testFindCompetenceByDomaine() {
		Domaine domaine = new Domaine();
		Competence competence = new Competence();
		competence.setDomaine(domaine);
		domaineDAO.createDomaine(domaine);
		competenceDAO.createCompetence(competence);		
		List<Competence> competences = competenceDAO.findCompetenceByDomaine(domaine);
		assertTrue(competences.contains(competence));
	}

	@Test
	public void testFindCompetenceByAcronym() {
		String codeComp = "D1";

		Competence competence = new Competence();
		competence.setCodeCompetence(codeComp);
		competenceDAO.createCompetence(competence);
		
		Competence comp = competenceDAO.findByAcronym(codeComp);
		assertTrue(competence.getCodeCompetence().equals(
				comp.getCodeCompetence()));
	}

}
