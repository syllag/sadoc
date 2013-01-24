package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
import fr.univartois.ili.sadoc.entitiesws.classes.Certificate;
import fr.univartois.ili.sadoc.entitiesws.classes.Owner;
import fr.univartois.ili.sadoc.entitiesws.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entitiesws.dao.PersistenceProvider;

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
		final CertificateDAO certificateDAO = new CertificateDAO();
		final OwnerDAO ownerDAO = new OwnerDAO();
		KeyPairGenerator ke2 = null;
		try {
			ke2 = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		ke2.initialize(1024, new SecureRandom());
		
		KeyPair k2 = ke2.generateKeyPair();
		final Certificate certificate = new Certificate();
		
		owner.setFirstName("Albert");
		owner.setLastName("Oisal");
		owner.setMail("albert.oisal@gmail.com");
		certificate.setPrivateKey(k2.getPrivate());
		certificate.setPublicKey(k2.getPublic());
		ArrayList<Certificate> liste = new ArrayList<Certificate>();
		liste.add(certificate);
		owner.setCertificates(liste);
		
		certificateDAO.create(certificate);
		
		ownerDAO.create(owner);
		
		Owner ownerTest = ownerDAO.findById(owner.getId());
		
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
