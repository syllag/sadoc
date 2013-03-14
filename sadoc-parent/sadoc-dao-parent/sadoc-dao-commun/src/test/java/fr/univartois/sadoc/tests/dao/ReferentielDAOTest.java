package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;


public class ReferentielDAOTest {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static ICompetenceDAO competenceDAO;
	private static IReferentielDAO referentielDAO;
	private static ApplicationContext appContext;	
	private Referentiel referentiel;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		competenceDAO = appContext.getBean("competenceDAO",ICompetenceDAO.class);
		referentielDAO = appContext.getBean("referentielDAO",IReferentielDAO.class);
		assertNotNull(competenceDAO);
	}
	
	
	@Test
	public void testCreate() {		
		referentiel = new Referentiel();
		referentielDAO.createReferentiel(referentiel);
		Referentiel r = referentielDAO.findReferentielById(referentiel.getId());
		assertEquals(referentiel,r);
		referentielDAO.removeReferentiel(referentiel);	
	}

	@Test
	public void testSearchId() {
		Referentiel referentiel = new Referentiel();		
		referentielDAO.createReferentiel(referentiel);
		Referentiel referentiels = referentielDAO.findReferentielById(referentiel.getId());	
		assertEquals(referentiel,referentiels);	
		referentielDAO.removeReferentiel(referentiel);
	}


}
