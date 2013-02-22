package fr.univartois.sadoc.ui.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.services.MetierUIServices;
import fr.univartois.ili.sadoc.ui.actions.CheckDocument;
import fr.univartois.ili.sadoc.ui.actions.CreateResume;
import fr.univartois.ili.sadoc.ui.actions.DownloadP7S;
import fr.univartois.ili.sadoc.ui.actions.DownloadResume;
import fr.univartois.ili.sadoc.ui.actions.ManageConnect;
import fr.univartois.ili.sadoc.ui.actions.ManageProfile;
import fr.univartois.ili.sadoc.ui.actions.ManageResume;
import fr.univartois.ili.sadoc.ui.actions.ManageSignIn;
import fr.univartois.ili.sadoc.ui.actions.ModifyUrl;
import fr.univartois.ili.sadoc.ui.actions.ShowCreateResume;
import fr.univartois.ili.sadoc.ui.actions.TestManageProfile;
import fr.univartois.ili.sadoc.ui.actions.VerifyDocument;
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
		
		CreateResume cr = new CreateResume() ;
		assertNotNull(cr.getMetierUIServices());
		
		DownloadP7S dp = new DownloadP7S();
		assertNotNull(dp.getMetierUIService());
		
		DownloadResume dr = new DownloadResume() ;
		assertNotNull(dr.getMetierUIService());
		
		ManageConnect mc = new ManageConnect();
		assertNotNull(mc.getMetierUIService());
		
		ManageProfile mp = new ManageProfile();
		assertNotNull(mp.getMetierUIService());
		
		ManageResume mr = new ManageResume();
		assertNotNull(mr.getMetierUIServices());
		
		ManageSignIn ms = new ManageSignIn();
		assertNotNull(ms.getMetierUIServices());
		
		ModifyUrl mu = new ModifyUrl();
		assertNotNull(mu.getMetierUIServices());
		
		ShowCreateResume scr = new ShowCreateResume();
		assertNotNull(scr.getMetierUIServices());
		
		VerifyDocument vd = new VerifyDocument() ;
		assertNotNull(vd.getMetierUIServices());
	}

}
