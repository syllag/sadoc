package fr.univartois.ili.sadoc.sadocweb.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

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
import fr.univartois.ili.sadoc.sadocweb.spring.WSPrivate;

public class WSPrivateTest {

	private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
			"spring-config.xml");

	private WSPrivate wsprivate;

	@Before
	public void initTest() {		
        PersistenceProvider.setProvider("sadocjpatest");
		wsprivate = (WSPrivate) APPLICATION_CONTEXT.getBean("wsPrivate");
	}

	@Test
	public void verifyDocumentTest(){
		
	}
	
	@Test
	public void getDocumentInformationsTest(){
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

		DocumentDAO.create(doc1);
		DocumentDAO.create(doc2);
		DocumentDAO.create(doc3);

		CompetenceDAO.create(comp1);
		CompetenceDAO.create(comp2);
		CompetenceDAO.create(comp3);

		OwnerDAO.create(owner1);
		OwnerDAO.create(owner2);

		CertificateDAO.create(certif);
		
		SignatureDAO.create(sig1);
		SignatureDAO.create(sig2);
		SignatureDAO.create(sig3);
		SignatureDAO.create(sig4);
		SignatureDAO.create(sig5);
		
		Map<Owner, List<Competence>> map1 = wsprivate.getDocumentInformations(doc1.getId());
		Map<Owner, List<Competence>> map2 = wsprivate.getDocumentInformations(doc2.getId());
		Map<Owner, List<Competence>> map3 = wsprivate.getDocumentInformations(doc3.getId());

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
	
	@Test
	public void importDocumentByOwnerTest(){
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
		
		DocumentDAO.create(doc1);
		DocumentDAO.create(doc2);
		DocumentDAO.create(doc3);

		CompetenceDAO.create(comp1);
		CompetenceDAO.create(comp2);
		CompetenceDAO.create(comp3);

		OwnerDAO.create(owner1);
		OwnerDAO.create(owner2);

		CertificateDAO.create(certif);
		
		SignatureDAO.create(sig1);
		SignatureDAO.create(sig2);
		SignatureDAO.create(sig3);
		SignatureDAO.create(sig4);
		SignatureDAO.create(sig5);
		
		List<Document> documents1 = wsprivate.importDocument(owner1);
		List<Document> documents2 = wsprivate.importDocument(owner2);
		
		assertTrue(documents1.contains(doc1));
		assertTrue(documents1.contains(doc2));
		assertFalse(documents1.contains(doc3));
		assertFalse(documents2.contains(doc1));
		assertFalse(documents2.contains(doc2));
		assertTrue(documents2.contains(doc3));
	}
	
	@Test
	public void importCompetencesByDocumentTest(){
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
		
		DocumentDAO.create(doc1);
		DocumentDAO.create(doc2);
		DocumentDAO.create(doc3);

		CompetenceDAO.create(comp1);
		CompetenceDAO.create(comp2);
		CompetenceDAO.create(comp3);

		OwnerDAO.create(owner1);
		OwnerDAO.create(owner2);

		CertificateDAO.create(certif);
		
		SignatureDAO.create(sig1);
		SignatureDAO.create(sig2);
		SignatureDAO.create(sig3);
		SignatureDAO.create(sig4);
		SignatureDAO.create(sig5);
		
		List<Competence> competences1 = wsprivate.importCompetences(doc1);
		List<Competence> competences2 = wsprivate.importCompetences(doc2);
		List<Competence> competences3 = wsprivate.importCompetences(doc3);

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
    public void endTests(){
            PersistenceProvider.removeProvider();
    }

}
