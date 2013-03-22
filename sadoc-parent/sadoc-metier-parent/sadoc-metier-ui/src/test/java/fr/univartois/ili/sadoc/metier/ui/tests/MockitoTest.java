/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ui.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.client.webservice.tools.Owner;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
@Ignore
public class MockitoTest {

	@Test
	public void testApplicationContextLoadByClasspathProcess(){
		ClientWebServiceImpl clientWebServiceImpl = new ClientWebServiceImpl();
		assertNotNull(clientWebServiceImpl);
		assertNotNull(clientWebServiceImpl.getApplicationContext());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate());
		System.out.println(clientWebServiceImpl.getWebServiceTemplate().getDefaultUri());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate().getMarshaller());
		assertNotNull(clientWebServiceImpl.getWebServiceTemplate().getUnmarshaller());
		String mail = UUID.randomUUID().toString().replace("-", "")+"@gmail.com";
		Owner owner = clientWebServiceImpl.createOwner(mail);
		assertTrue(owner.getId()>0);
		assertEquals(mail, owner.getMailInitial());
	}
}