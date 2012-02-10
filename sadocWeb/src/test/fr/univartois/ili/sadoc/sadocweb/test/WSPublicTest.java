package fr.univartois.ili.sadoc.sadocweb.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Signature;
import fr.univartois.ili.sadoc.sadocweb.spring.WSPublic;

public class WSPublicTest {

	private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
			"spring-config.xml");

	private WSPublic wspublic;
	
	@Before
	public void initTest() {		
        PersistenceProvider.setProvider("sadocjpatest");
		wspublic = (WSPublic) APPLICATION_CONTEXT.getBean("wsPublic");
	}
	
	@Test
	public void getCertificateTest(){
		Owner owner1 = new Owner("firstname1", "lastName1", "mail1");
		Owner owner2 = new Owner("firstname2", "lastName2", "mail2");
		KeyPairGenerator ke2 = null;
		try {
			ke2 = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		ke2.initialize(1024, new SecureRandom());
		
		KeyPair k2 = ke2.generateKeyPair();
		Certificate certif1 = new Certificate(k2.getPublic(), k2.getPrivate(), owner1);
		Certificate certif2 = new Certificate(k2.getPublic(), k2.getPrivate(), owner1);
		Certificate certif3 = new Certificate(k2.getPublic(), k2.getPrivate(), owner1);

		
		
		OwnerDAO.create(owner1);
		OwnerDAO.create(owner2);

		CertificateDAO.create(certif1);
		CertificateDAO.create(certif2);
		CertificateDAO.create(certif3);
		
		List<Certificate> certificates1 = wspublic.getCertificate(owner1);
		List<Certificate> certificates2 = wspublic.getCertificate(owner2);
		
		assertTrue(certificates1.contains(certif1));
		assertTrue(certificates1.contains(certif2));
		assertFalse(certificates1.contains(certif3));
		assertFalse(certificates2.contains(certif1));
		assertFalse(certificates2.contains(certif2));
		assertTrue(certificates2.contains(certif3));
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }
}
