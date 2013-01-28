package fr.univartois.ili.sadoc.dao;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.domaine.Certificate;
import fr.univartois.ili.sadoc.domaine.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class CertificateTest {
	
	@Before
    public void initTests(){
            PersistenceProvider.setProvider("sadocjpatest");
            InitDataForTest.createDataForTest();
    }
	
	@Test
	public void testPersist() {
		final Certificate certificate = new Certificate();
		final Owner owner = new Owner();
		final OwnerDAO ownerDAO = new OwnerDAO();
		final CertificateDAO certificateDAO = new CertificateDAO();
		KeyPairGenerator ke2 = null;
		try {
			ke2 = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		ke2.initialize(1024, new SecureRandom());
		
		KeyPair k2 = ke2.generateKeyPair();
		certificate.setPrivateKey(k2.getPrivate());
		certificate.setPublicKey(k2.getPublic());
		owner.setFirstName("Albert");
		owner.setLastName("Oisal");
		owner.setMail("albert.oisal@gmail.com");
		certificate.setOwner(owner);
		
		ownerDAO.create(owner);
		
		certificateDAO.create(certificate);
		
		Certificate certificateTest = certificateDAO.findById(certificate.getId());
		
		assertEquals(certificate.getPrivateKey(),certificateTest.getPrivateKey());
		assertEquals(certificate.getPublicKey(),certificateTest.getPublicKey());
		assertEquals(certificate.getOwner(),certificateTest.getOwner());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }

	
}
