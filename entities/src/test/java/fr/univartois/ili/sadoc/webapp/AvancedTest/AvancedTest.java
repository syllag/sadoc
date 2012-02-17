package fr.univartois.ili.sadoc.webapp.AvancedTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author damien.wattiez <damien.wattiez at gmail.com>
 * 
 */
public class AvancedTest {

	@Before
	public void initTests() {
		PersistenceProvider.setProvider("sadocjpatest");

		// create data
		InitDataForTest initData = new InitDataForTest();
		initData.createDataForTest();
	}

	// test for Owner link

	@Test
	public void testListDegrees() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		TypedQuery<Owner> query1;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "diane");
		List<Owner> ownerDiane = query.getResultList();

		assertEquals("diane", ownerDiane.get(0).getFirstName());
		assertEquals(0, ownerDiane.get(0).getDegrees().size());

		query1 = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query1.setParameter("firstName", "kevin");
		List<Owner> ownerKevin = query1.getResultList();

		assertEquals("kevin", ownerKevin.get(0).getFirstName());
		assertEquals(2, ownerKevin.get(0).getDegrees().size());
		assertEquals("C2I1", ownerKevin.get(0).getDegrees().get(0).getName());
		assertEquals("master", ownerKevin.get(0).getDegrees().get(1).getName());

	}

	@Test
	public void testListResume() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		TypedQuery<Owner> query1;

		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "diane");
		List<Owner> ownerDiane = query.getResultList();

		assertEquals("diane", ownerDiane.get(0).getFirstName());
		assertEquals(0, ownerDiane.get(0).getResumes().size());

		query1 = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query1.setParameter("firstName", "kevin");
		List<Owner> ownerKevin = query1.getResultList();

		assertEquals("kevin", ownerKevin.get(0).getFirstName());
		assertEquals(2, ownerKevin.get(0).getDegrees().size());
	}

	@Test
	public void testListCompetence() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "jimmy");
		List<Owner> ownerJimmy = query.getResultList();

		assertEquals("jimmy", ownerJimmy.get(0).getFirstName());
		assertEquals(1, ownerJimmy.get(0).getDegrees().size());
		assertEquals(4, ownerJimmy.get(0).getDegrees().get(0).getCompetences()
				.size());
	}
	

	// other test

	@Test
	public void testListCompetenceFromAcquisition() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Competence> query;
		query = em.createQuery(
				"select distinct d FROM Competence d WHERE d.name = :name ",
				Competence.class);
		
		query.setParameter("name", "competence 1 C2I1");
		List<Competence> competenceTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByCompetence(competenceTest.get(0));
		assertEquals(3, acquisitionTest.size());
	}

	@Test
	public void testListCompetenceFromAcquisitionOwner() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "jimmy");
		List<Owner> ownerJimmy = query.getResultList();

		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByOwner(ownerJimmy.get(0));
		assertEquals(6, acquisitionTest.size());

		assertEquals("competence semestre 1 licence, C2I1 et C2I2",
				acquisitionTest.get(0).getCompetence().getName());
		assertEquals("competence semestre 2 licence", acquisitionTest.get(1)
				.getCompetence().getName());
		assertEquals("competence semestre 3 licence", acquisitionTest.get(2)
				.getCompetence().getName());
		assertEquals("competence 1 C2I1", acquisitionTest.get(3)
				.getCompetence().getName());
		assertEquals("competence semestre 4 licence et master", acquisitionTest
				.get(4).getCompetence().getName());
		assertEquals("competence 3 C2I2", acquisitionTest.get(5)
				.getCompetence().getName());

	}

	@Test
	public void testListCompetenceFromAcquisitionDocument() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Document> query;
		query = em.createQuery(
				"select d FROM Document d WHERE d.name = :name ",
				Document.class);
		query.setParameter("name", "document1");
		List<Document> documentTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByDocument(documentTest.get(0));
		assertEquals(3, acquisitionTest.size());
		assertEquals("competence 1 C2I1",
				acquisitionTest.get(0).getCompetence().getName());
		assertEquals("competence 2 C2I1",
				acquisitionTest.get(1).getCompetence().getName());
		assertEquals("competence semestre 2 master",
				acquisitionTest.get(2).getCompetence().getName());
	}
	
	
	@Test
	public void testListDocumentFromAcquisition() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Document> query;
		query = em.createQuery(
				"select distinct d FROM Document d WHERE d.name = :name ",
				Document.class);
		query.setParameter("name", "document1");
		List<Document> documentTest = query.getResultList();

		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByDocument(documentTest.get(0));
		assertEquals(3, acquisitionTest.size());
	}

	@Test
	public void testListDocumentFromAcquisitionOwner() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "jimmy");
		List<Owner> ownerJimmy = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByOwner(ownerJimmy.get(0));
		assertEquals(6, acquisitionTest.size());
		assertEquals("document3",
				acquisitionTest.get(0).getDocument().getName());
		assertEquals("document4",
				acquisitionTest.get(3).getDocument().getName());
		assertEquals("document5",
				acquisitionTest.get(4).getDocument().getName());
	}
	
	@Test
	public void testListDocumentFromAcquisitionCompetence() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Competence> query;
		query = em.createQuery(
				"select c FROM Competence c WHERE c.name = :name ",
				Competence.class);
		query.setParameter("name", "competence semestre 4 licence et master");
		List<Competence> competenceTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByCompetence(competenceTest.get(0));
		assertEquals(3, acquisitionTest.size());
		assertEquals("document2",
				acquisitionTest.get(0).getDocument().getName());
		assertEquals("document5",
				acquisitionTest.get(1).getDocument().getName());
		assertEquals("document6",
				acquisitionTest.get(2).getDocument().getName());
	}
	
	@Test
	public void testOwnerFromAcquisition() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Owner> query;
		query = em.createQuery(
				"select o FROM Owner o WHERE o.firstName = :firstName ",
				Owner.class);
		query.setParameter("firstName", "francois");
		List<Owner> ownerTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByOwner(ownerTest.get(0));

		assertEquals(4, acquisitionTest.size());
	}
	
	@Test
	public void testListOwnerFromAcquisitionCompetence() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Competence> query;
		query = em.createQuery(
				"select c FROM Competence c WHERE c.name = :name ",
				Competence.class);
		query.setParameter("name", "competence semestre 2 licence");
		List<Competence> competenceTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByCompetence(competenceTest.get(0));
		
		assertEquals(2, acquisitionTest.size());
		assertEquals("jimmy",
				acquisitionTest.get(0).getOwner().getFirstName());
		assertEquals("kevin",
				acquisitionTest.get(1).getOwner().getFirstName());
	}
	
	@Test
	public void testListOwnerFromAcquisitionDocument() {
		EntityManager em = PersistenceProvider.getEntityManager();
		TypedQuery<Document> query;
		query = em.createQuery(
				"select distinct d FROM Document d WHERE d.name = :name ",
				Document.class);
		query.setParameter("name", "document2");
		List<Document> documentTest = query.getResultList();
		List<Acquisition> acquisitionTest = AcquisitionDAO
				.findByDocument(documentTest.get(0));
		assertEquals(1, acquisitionTest.size());
		assertEquals("francois",
				acquisitionTest.get(0).getOwner().getFirstName());
	}
	

	
	@After
	public void endTests() {
		PersistenceProvider.removeProvider();
	}

}
