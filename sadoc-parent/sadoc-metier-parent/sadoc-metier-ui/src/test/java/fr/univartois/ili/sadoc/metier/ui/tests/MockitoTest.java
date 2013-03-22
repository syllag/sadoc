/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ui.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
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
		clientWebServiceImpl.createOwner("alan.delahayee@gmail.com");
		
		//arrange
//				MetierUIServices m = mock(MetierUIServices.class);
//				when(m.findDocumentById("3")).thenReturn(new Document("Doc", "12", "toto", null, null));
//				//act
//				Document doc = m.findDocumentById("3");
//				//assert
//				assertNotNull(doc);
//				assertEquals(doc.getName(), "Doc");
//				doc = m.findDocumentById("0");
//				assertNotNull(doc);
//				assertEquals(doc.getName(), "Doc 1");
	}
}