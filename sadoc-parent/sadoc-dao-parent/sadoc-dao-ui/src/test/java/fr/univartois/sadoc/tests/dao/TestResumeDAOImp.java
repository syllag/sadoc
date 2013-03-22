package fr.univartois.sadoc.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Owner;
import fr.univartois.ili.sadoc.dao.entities.Resume;
import fr.univartois.ili.sadoc.dao.services.IOwnerDAO;
import fr.univartois.ili.sadoc.dao.services.IResumeDAO;

public class TestResumeDAOImp {


	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static ApplicationContext appContext;
	private static IResumeDAO resumeDAO;
	private static IOwnerDAO ownerDAO;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		resumeDAO = appContext.getBean("resumeDAO", IResumeDAO.class);
		ownerDAO = appContext.getBean("ownerDAO", IOwnerDAO.class);
		assertNotNull(resumeDAO);
		assertNotNull(ownerDAO);
	}
	
	
	private Owner getFakeOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Toto");
		owner.setLastName("TITI");
		owner.setMail("toto@mail.ie");
		owner.setPassword("xxxxxxxxxxxxxxx");
		owner.setAddress("1, rue des informaticiens");
		owner.setTown("LENS");
		owner.setPhone("0123456789");
		owner.setIdOwnerWs(1);
		owner.setResumes(new ArrayList<Resume>());
		return owner;
	}
	
	@Test
	public void testCreateGenerateId() {		
		Resume resume = new Resume();
		assertEquals(0, resume.getId());
		resumeDAO.createResume(resume);
		assertTrue(resume.getId() != 0);
	}
	
	@Test
	public void testCreateWithOwner() {
		Resume resume = new Resume();
		Owner owner= getFakeOwner();
		ownerDAO.createOwner(owner);
		resume.setOwner(owner);
		owner.getResumes().add(resume);
		resumeDAO.createResume(resume);
		ownerDAO.updateOwner(owner);
		
		Owner ownerFound = ownerDAO.findOwnerById(owner.getId());
		assertEquals(1, ownerFound.getResumes().size());
		assertTrue(ownerFound.getResumes().contains(resume));
		Resume resumeFound = resumeDAO.findResumeById(resume.getId());
		assertEquals(resume.getOwner(), resumeFound.getOwner());
	}
	
	@Test
	public void testModifyReferentiels() {		
		Resume resume = new Resume();
		resume.getReferentiels().add(new Long(1));
		resume.getDomaines().add(new Long(1));
		resume.getCompetences().add(new Long(1));
		resume.getItems().add(new Long(1));
		
		assertEquals(1, resume.getReferentiels().size());
		assertEquals(1, resume.getDomaines().size());
		assertEquals(1, resume.getCompetences().size());
		assertEquals(1, resume.getItems().size());
		resumeDAO.createResume(resume);
		
		// Add elements into Lists
		resume.getReferentiels().add(new Long(2));
		resume.getDomaines().add(new Long(2));
		resume.getCompetences().add(new Long(2));
		resume.getItems().add(new Long(2));
		resumeDAO.updateResume(resume);
		
		// Test
		Resume resumeFound = resumeDAO.findResumeById(resume.getId());
		assertEquals(2, resumeFound.getReferentiels().size());
		assertEquals(2, resumeFound.getDomaines().size());
		assertEquals(2, resumeFound.getCompetences().size());
		assertEquals(2, resumeFound.getItems().size());
		
		// Delete elements from Lists
		resume.getReferentiels().remove(1);
		resume.getDomaines().remove(1);
		resume.getCompetences().remove(1);
		resume.getItems().remove(1);
		resumeDAO.updateResume(resume);
		
		// Test
		resumeFound = resumeDAO.findResumeById(resume.getId());
		assertEquals(1, resumeFound.getReferentiels().size());
		assertEquals(1, resumeFound.getDomaines().size());
		assertEquals(1, resumeFound.getCompetences().size());
		assertEquals(1, resumeFound.getItems().size());
		
		
		// Remove elements into lists
		
		
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		list.add(new Long(2));
		resume.setCompetences(list);

		resumeFound = resumeDAO.findResumeById(resume.getId());
		assertEquals(resume, resumeFound);
	}
	
	@Test
	public void testRemove() {	
		Resume resume = new Resume();
		resumeDAO.createResume(resume);
		resumeDAO.removeResume(resume);
		
		Resume resumeFound=resumeDAO.findResumeById(resume.getId());
		assertNull(resumeFound);
	}
}
