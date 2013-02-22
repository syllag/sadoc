/**
 * 
 */
package fr.univartois.sadoc.ui.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univartois.ili.sadoc.ui.actions.CheckDocument;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */

public class AllTests {
	
	@Test
	public void testInjectionForMetierUiServiceInCheckDocument() {		
		CheckDocument cd = new CheckDocument() ;
		assertNotNull(cd.getMetierUIService());
		// test purposes only
		assertEquals("toto",cd.getMetierUIService().findOwnerById(0).getFirstName());
	}

}
