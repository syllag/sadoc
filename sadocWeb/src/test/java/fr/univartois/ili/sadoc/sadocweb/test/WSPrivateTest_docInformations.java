package fr.univartois.ili.sadoc.sadocweb.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;
import fr.univartois.ili.sadoc.sadocweb.spring.WSPrivate;

public class WSPrivateTest_docInformations {

	private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
			"spring-config.xml");

	private WSPrivate wsprivate;

	@Before
	public void initTest() {
		PersistenceProvider.setProvider("sadocjpatest");
		wsprivate = (WSPrivate) APPLICATION_CONTEXT.getBean("wsPrivate");
	}

	@Test
	public void verifyDocumentTest() {

	}

	@Test
	public void getDocumentInformationsTest() {
		final DocumentDAO documentDAO = new DocumentDAO();
		final CompetenceDAO competenceDAO = new CompetenceDAO();
		final OwnerDAO ownerDAO = new OwnerDAO();
		final CertificateDAO certificateDAO = new CertificateDAO();
		final SignatureDAO signatureDAO = new SignatureDAO();
		final EntityManager entityManager = PersistenceProvider
				.getEntityManager();

		Document doc1 = new Document("Document 1", "1", "test".getBytes());
		Document doc2 = new Document("Document 2", "2", "test".getBytes());
		Document doc3 = new Document("Document 3", "3", "test".getBytes());

		Competence comp1 = new Competence("Competence 1", "Description 1");
		Competence comp2 = new Competence("Competence 2", "Description 2");
		Competence comp3 = new Competence("Competence 3", "Description 3");

		Owner owner1 = new Owner("firstname1", "lastName1", "mail1");
		Owner owner2 = new Owner("firstname2", "lastName2", "mail2");

		Certificate certif = new Certificate();

		Signature sig1 = new Signature(doc1, owner1, comp1, certif);
		Signature sig2 = new Signature(doc2, owner1, comp2, certif);
		Signature sig3 = new Signature(doc2, owner1, comp3, certif);
		Signature sig4 = new Signature(doc3, owner2, comp2, certif);
		Signature sig5 = new Signature(doc3, owner2, comp3, certif);

		entityManager.getTransaction().begin();

		documentDAO.create(doc1);
		documentDAO.create(doc2);
		documentDAO.create(doc3);

		competenceDAO.create(comp1);
		competenceDAO.create(comp2);
		competenceDAO.create(comp3);

		ownerDAO.create(owner1);
		ownerDAO.create(owner2);

		certificateDAO.create(certif);

		signatureDAO.create(sig1);
		signatureDAO.create(sig2);
		signatureDAO.create(sig3);
		signatureDAO.create(sig4);
		signatureDAO.create(sig5);

		entityManager.getTransaction().commit();

		Map<Owner, List<Competence>> map1 = wsprivate
				.getDocumentInformations(doc1.getId());
		Map<Owner, List<Competence>> map2 = wsprivate
				.getDocumentInformations(doc2.getId());
		Map<Owner, List<Competence>> map3 = wsprivate
				.getDocumentInformations(doc3.getId());

		assertTrue(map1.containsKey(owner1));
		assertFalse(map1.containsKey(owner2));
		assertTrue(map2.containsKey(owner1));
		assertFalse(map2.containsKey(owner2));
		assertFalse(map3.containsKey(owner1));
		assertTrue(map3.containsKey(owner2));

		List<Competence> competences1 = map1.get(owner1);
		List<Competence> competences2 = map2.get(owner1);
		List<Competence> competences3 = map3.get(owner2);

		assertTrue(competences1.contains(comp1));
		assertFalse(competences1.contains(comp2));
		assertFalse(competences1.contains(comp3));
		assertFalse(competences2.contains(comp1));
		assertTrue(competences2.contains(comp2));
		assertTrue(competences2.contains(comp3));
		assertFalse(competences3.contains(comp1));
		assertTrue(competences3.contains(comp2));
		assertTrue(competences3.contains(comp3));
		
	}



	

	@After
	public void endTests() {
		PersistenceProvider.removeProvider();
		
		
		
	}

}
