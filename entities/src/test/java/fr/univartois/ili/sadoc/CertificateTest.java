package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class CertificateTest {
	
	@Before
    public void initTests(){
            PersistenceProvider.setProvider("sadocjpatest");
    }
	
	@Test
	public void testPersist() {
		final Certificate certificate = new Certificate();
		final Owner owner = new Owner();
				
		certificate.setPrivateKey("zrgf54tgh64th0t4h501gr0f4r85gf");
		certificate.setPublicKey("fkforf48478g4h001z5f4ze10f84");
		owner.setFirstName("Albert");
		owner.setLastName("Oisal");
		owner.setMail("albert.oisal@gmail.com");
		certificate.setOwner(owner);
		
		OwnerDAO.create(owner);
		
		CertificateDAO.create(certificate);
		
		Certificate certificateTest = CertificateDAO.findById(certificate.getId());
		
		assertEquals(certificate.getPrivateKey(),certificateTest.getPrivateKey());
		assertEquals(certificate.getPublicKey(),certificateTest.getPublicKey());
		assertEquals(certificate.getOwner(),certificateTest.getOwner());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }

	
}
