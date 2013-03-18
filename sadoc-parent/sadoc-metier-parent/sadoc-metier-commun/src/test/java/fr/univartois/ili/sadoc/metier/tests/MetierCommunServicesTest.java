package fr.univartois.ili.sadoc.metier.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Item;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;
import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;

public class MetierCommunServicesTest {
	
	private ApplicationContext ac;
	private IMetierCommunServices mcs;
	
	@Before
	public void setUp(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		mcs=(IMetierCommunServices)ac.getBean("metierCommunServices");
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithNoArgument(){
		mcs.isValideAcronym(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithEmptyString(){
		mcs.isValideAcronym("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithUnauthorizedStringFormat(){
		mcs.isValideAcronym(":12:::");
	}
	
	@Test
	public void testWithNoIDs() {
		String acronym=":::";
		
		assertFalse("The method must return false for the acronym \":::\"",mcs.isValideAcronym(acronym));
	}
	
	@Test
	public void testWithCorrectAcronymAndValidIDs() {
		assertTrue("The method must return true for the acronym \"1:::\"",mcs.isValideAcronym("1:::"));
		assertTrue("The method must return true for the acronym \"1:1::\"",mcs.isValideAcronym("1:1::"));
		assertTrue("The method must return true for the acronym \"1:2:1:\"",mcs.isValideAcronym("1:2:1:"));
		assertTrue("The method must return true for the acronym \"1:2:1:1\"",mcs.isValideAcronym("1:2:1:1"));
	}
	
	@Test
	public void testWithCorrectAcronymAndInexistantIDs() {
		assertFalse("The method must return false for the acronym \"154:::\"",mcs.isValideAcronym("154:::"));
		assertFalse("The method must return false for the acronym \"1:487::\"",mcs.isValideAcronym("1:487::"));
		assertFalse("The method must return false for the acronym \"1:1:157:\"",mcs.isValideAcronym("1:1:157:"));
		assertFalse("The method must return false for the acronym \"1:1:1:184\"",mcs.isValideAcronym("1:1:1:184"));
	}
	
	@Test
	public void testWithIncorrectAcronym(){
		assertFalse("The method must return false for the acronym \":1::\"",mcs.isValideAcronym(":1::"));
		assertFalse("The method must return false for the acronym \"::1:\"",mcs.isValideAcronym("::1:"));
		assertFalse("The method must return false for the acronym \":::12\"",mcs.isValideAcronym(":::12"));
		assertFalse("The method must return false for the acronym \":1:12:\"",mcs.isValideAcronym(":1:12:"));
		assertFalse("The method must return false for the acronym \":1::17\"",mcs.isValideAcronym(":1::17"));
		assertFalse("The method must return false for the acronym \"::15:18875\"",mcs.isValideAcronym("::15:18875"));
	}

}
