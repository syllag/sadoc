package fr.univartois.sadoc.ui.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.services.MetierUIServices;
import fr.univartois.ili.sadoc.ui.actions.CheckDocument;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class MetierUIServicesTest {

	@Test
	public void testInjectionInstanciation() {
		IMetierUIServices metier = ContextFactory.getContext().getBean(IMetierUIServices.class);
		assertNotNull(metier);
	}
	
	@Test
	public void testInjectionOwnerDAOInMetierUIServices() {
		MetierUIServices metier = ContextFactory.getContext().getBean(MetierUIServices.class);
		assertNotNull(metier.getOwnerDAO());
	}
	
	@Test
	public void testMetierInjectionInStrutsActions(){
		CheckDocument cd = new CheckDocument();
		assertNotNull(cd.getMetierUIService());
	}

}
