package fr.univartois.ili.sadoc.metier.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.client.webservice.tools.Owner;

public class GetOwnerTest {

	private static ClientWebServiceImpl clientWebServiceImpl;

	@BeforeClass
	public static void initialize() {
		clientWebServiceImpl = new ClientWebServiceImpl();
		assertNotNull(clientWebServiceImpl);
		assertNotNull(clientWebServiceImpl.getApplicationContext());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate()
				.getMarshaller());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate()
				.getUnmarshaller());
	}

	@Test
	public void testApplicationContextLoadByClasspathProcess() {
		String mail = UUID.randomUUID().toString().replace("-", "")
				+ "@gmail.com";
		Owner owner = clientWebServiceImpl.createOwner(mail);
		assertTrue(owner.getId() > 0);
		assertEquals(mail, owner.getMailInitial());
		Owner owner2 = clientWebServiceImpl.getOwner(mail);
		assertEquals(owner2.getId(), owner.getId());
		assertEquals(owner2.getMailInitial(), owner.getMailInitial());
		
	}
}
