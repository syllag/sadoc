package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;

public class DomaineDAOTest {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static IDomaineDAO domaineDAO;
	private static IReferentielDAO referentielDAO;
	private static ApplicationContext appContext;	

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		referentielDAO = appContext.getBean("referentielDAO",IReferentielDAO.class);
		domaineDAO = appContext.getBean("domaineDAO",IDomaineDAO.class);
		assertNotNull(domaineDAO);
	}
	
	@Test
	public void testSearchId() {
		Domaine domaine = new Domaine();
		domaine.setCodeDomaine("codeDomaine");
		domaine.setDescription("description");
		domaineDAO.createDomaine(domaine);
		Domaine domaineResults = domaineDAO.findDomaineById(domaine.getId());
		assertEquals(domaineResults, domaine);	
		domaineDAO.removeDomaine(domaine);
	}

	@Test
	public void testCreate() {
		Domaine domaine = new Domaine();
		domaineDAO.createDomaine(domaine);
		Domaine d = domaineDAO.findDomaineById(domaine.getId());
		assertEquals(domaine, d);
		assertNotNull("id not null", domaine.getId());
		domaineDAO.removeDomaine(domaine);
	}

	@Test
	public void testSearchReferentiel() {
		Domaine domaine = new Domaine();
		Referentiel referentiel = new Referentiel();
		
		referentielDAO.createReferentiel(referentiel);
		domaine.setReferentiel(referentiel);
		domaineDAO.createDomaine(domaine);
		
		List<Domaine> domaineResults = domaineDAO
				.findDomaineByReferentiel(referentiel);
		assertTrue(domaineResults.contains(domaine));
		
		
		domaineDAO.removeDomaine(domaine);
		referentielDAO.removeReferentiel(referentiel);
	}
}
