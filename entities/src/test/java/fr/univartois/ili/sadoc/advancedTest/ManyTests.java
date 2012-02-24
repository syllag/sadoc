package fr.univartois.ili.sadoc.advancedTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class ManyTests {

	private SignatureDAO signatureDAO;

	@BeforeClass
	public static void initTests() {
		PersistenceProvider.setProvider("sadocjpatest");
		InitDataForTest.createDataForTest();
	}

	@Test
	public void getDocumentsFromOwner() {
		signatureDAO = new SignatureDAO();
		EntityManager em = PersistenceProvider.getEntityManager();
		List<Owner> owners = em.createQuery(
				"SELECT o FROM Owner o ORDER BY o.id", Owner.class)
				.getResultList();
		List<Document> documents1 = signatureDAO.findDocumentByOwner(owners
				.get(0));
		assertEquals(documents1.get(0).getName(), "document3");
		assertEquals(documents1.get(1).getName(), "document6");
		List<Document> documents2 = signatureDAO.findDocumentByOwner(owners
				.get(1));
		assertEquals(documents2.get(0).getName(), "document1");
		assertEquals(documents2.get(1).getName(), "document2");
		List<Document> documents3 = signatureDAO.findDocumentByOwner(owners
				.get(2));
		assertEquals(documents3.get(0).getName(), "document4");
		assertEquals(documents3.get(1).getName(), "document8");
		List<Document> documents4 = signatureDAO.findDocumentByOwner(owners
				.get(3));
		assertEquals(documents4.get(0).getName(), "document5");
		assertEquals(documents4.get(1).getName(), "document7");
	}

	@Test
	public void getCompetencesFromDocument() {
		signatureDAO = new SignatureDAO();
		EntityManager em = PersistenceProvider.getEntityManager();
		List<Document> documents = em.createQuery("SELECT d FROM Document d ORDER BY d.id",
				Document.class).getResultList();
		// Set<Document> docs = new HashSet<Document>(documents);
		// documents = new ArrayList<Document> (docs);
		// Collections.sort(documents);

		System.out.println(documents.get(0).getName());
		System.out.println(documents.get(1).getName());
		System.out.println(documents.get(2).getName());
		System.out.println(documents.get(3).getName());
		System.out.println(documents.get(4).getName());
		System.out.println(documents.get(5).getName());
		System.out.println(documents.get(6).getName());
		System.out.println(documents.get(7).getName());

		List<Competence> competences1 = signatureDAO
				.findCompetenceByDocument(documents.get(0));
		assertEquals(competences1.get(0).getName(), "competence01");
		assertEquals(competences1.get(1).getName(), "competence05");
		assertEquals(competences1.get(2).getName(), "competence10");
		List<Competence> competences2 = signatureDAO
				.findCompetenceByDocument(documents.get(1));
		assertEquals(competences2.get(0).getName(), "competence02");
		assertEquals(competences2.get(1).getName(), "competence06");
		assertEquals(competences2.get(2).getName(), "competence12");
		List<Competence> competences3 = signatureDAO
				.findCompetenceByDocument(documents.get(2));
		assertEquals(competences3.get(0).getName(), "competence03");
		assertEquals(competences3.get(1).getName(), "competence08");
		assertEquals(competences3.get(2).getName(), "competence11");
		List<Competence> competences4 = signatureDAO
				.findCompetenceByDocument(documents.get(3));
		assertEquals(competences4.get(0).getName(), "competence02");
		assertEquals(competences4.get(1).getName(), "competence04");
		assertEquals(competences4.get(2).getName(), "competence07");
		List<Competence> competences5 = signatureDAO
				.findCompetenceByDocument(documents.get(4));
		assertEquals(competences5.get(0).getName(), "competence03");
		assertEquals(competences5.get(1).getName(), "competence06");
		assertEquals(competences5.get(2).getName(), "competence09");
		List<Competence> competences6 = signatureDAO
				.findCompetenceByDocument(documents.get(5));
		assertEquals(competences6.get(0).getName(), "competence07");
		assertEquals(competences6.get(1).getName(), "competence08");
		assertEquals(competences6.get(2).getName(), "competence11");
		List<Competence> competences7 = signatureDAO
				.findCompetenceByDocument(documents.get(6));
		assertEquals(competences7.get(0).getName(), "competence10");
		assertEquals(competences7.get(1).getName(), "competence11");
		assertEquals(competences7.get(2).getName(), "competence12");
		List<Competence> competences8 = signatureDAO
				.findCompetenceByDocument(documents.get(7));
		assertEquals(competences8.get(0).getName(), "competence01");
		assertEquals(competences8.get(1).getName(), "competence06");
		assertEquals(competences8.get(2).getName(), "competence09");
	}

	@AfterClass
	public static void endTests() {
		PersistenceProvider.removeProvider();
	}
}
