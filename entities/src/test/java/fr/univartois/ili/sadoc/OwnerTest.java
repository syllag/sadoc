package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class OwnerTest {
	
	@Before
    public void initTests(){
            PersistenceProvider.setProvider("sadocjpatest");
            InitDataForTest.createDataForTest();
    }
	
	@Test
	public void testPersist() {
		final Owner owner = new Owner();
		final Certificate certificate = new Certificate();
		
		owner.setFirstName("Albert");
		owner.setLastName("Oisal");
		owner.setMail("albert.oisal@gmail.com");
		certificate.setPrivateKey("zf54gr84g0tg456g40g4r54");
		certificate.setPublicKey("zer5erf564rg4rgzr04g4r4");
		ArrayList<Certificate> liste = new ArrayList<Certificate>();
		liste.add(certificate);
		owner.setCertificates(liste);
		
		CertificateDAO.create(certificate);
		
		OwnerDAO.create(owner);
		
		Owner ownerTest = OwnerDAO.findById(owner.getId());
		
		assertEquals(owner.getFirstName(),ownerTest.getFirstName());
		assertEquals(owner.getLastName(),ownerTest.getLastName());
		assertEquals(owner.getMail(),ownerTest.getMail());
		assertEquals(owner.getCertificates(),ownerTest.getCertificates());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }
}
