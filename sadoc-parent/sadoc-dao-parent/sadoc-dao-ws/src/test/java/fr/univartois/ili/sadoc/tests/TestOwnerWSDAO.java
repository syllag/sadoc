package fr.univartois.ili.sadoc.tests;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.IOwnerWSDAO;


public class TestOwnerWSDAO {

	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";
	private static IOwnerWSDAO ownerDAO;
	private static ApplicationContext appContext;
	private OwnerWS owner;
	private int id = 1;

	@BeforeClass
	public static void testGetEntityManager() {
		appContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		ownerDAO = appContext.getBean("ownerDAO",IOwnerWSDAO.class);
		assertNotNull(ownerDAO);
	}

	@Before
	public void createUser() {
		owner = new OwnerWS();
		owner.setMail_initial(UUID.randomUUID().toString().replace("-", "")
				+ "@toto.com");
		owner.setId(id++);
		ownerDAO.create(owner);
	}

	@Test
	public void testGetByEmail() {
		System.err.println(owner);
		OwnerWS first = ownerDAO.findById(owner.getId());
		assertNotNull(first);
		OwnerWS test = ownerDAO.findByMail(this.owner.getMail_initial());
		assertNotNull(test);
	}

}
