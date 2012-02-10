package fr.univartois.ili.sadoc.sadocweb.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
		Certificate certif1 = new Certificate("key1", "key1", owner1);
		Certificate certif2 = new Certificate("key2", "key2", owner1);
		Certificate certif3 = new Certificate("key3", "key3", owner2);

		
		
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
