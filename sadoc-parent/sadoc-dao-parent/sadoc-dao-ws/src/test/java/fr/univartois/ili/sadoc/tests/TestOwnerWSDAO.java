package fr.univartois.ili.sadoc.tests;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.AbstractWebServiceDAO;
import fr.univartois.ili.sadoc.dao.OwnerWSDAO;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.IOwnerDAO;

public class TestOwnerWSDAO {

	private static IOwnerDAO ownerDAO;
	private OwnerWS owner;
	
	@BeforeClass
	public static void testGetEntityManager() {
		ownerDAO = new OwnerWSDAO();
		AbstractWebServiceDAO wsDao = (AbstractWebServiceDAO) ownerDAO;
		assertNotNull(wsDao);
		assertNotNull(wsDao.getEntityManager());
	}
	
	@Before
	public void createUser(){
		owner = new OwnerWS();
		owner.setMail_initial( UUID.randomUUID().toString().replace("-", "")+"@toto.com");
		ownerDAO.create(owner);
	}
	
	@Test
	public void testGetByEmail()
	{
		System.err.println(owner);
		OwnerWS first = ownerDAO.findById(owner.getId());
		assertNotNull(first);
		OwnerWS test = ownerDAO.findByMail(this.owner.getMail_initial());
		assertNotNull(test);
	}

}
