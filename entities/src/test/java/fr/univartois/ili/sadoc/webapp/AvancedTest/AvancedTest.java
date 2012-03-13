package fr.univartois.ili.sadoc.webapp.AvancedTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author damien.wattiez <damien.wattiez at gmail.com>
 * 
 */
public class AvancedTest {

	private static AcquisitionDAO acquisitionDAO;
	
	@BeforeClass
	public static void initTests() {
		PersistenceProvider.setProvider("sadocjpatest");
		acquisitionDAO =new AcquisitionDAO();
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

		TypedQuery<Degree> query2;
		query2 = em.createQuery(
				"select d FROM Degree d WHERE d.name = :name ",
				Degree.class);
		query2.setParameter("name", "C2I1");
		List<Degree> c2i1 = query2.getResultList();
		ownerKevin.get(0).getDegrees().contains(c2i1);
		
		TypedQuery<Degree> query3;
		query3 = em.createQuery(
				"select d FROM Degree d WHERE d.name = :name ",
				Degree.class);
		query3.setParameter("name", "master");
		List<Degree> master = query3.getResultList();
		ownerKevin.get(0).getDegrees().contains(master);
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
		List<Acquisition> acquisitionTest = acquisitionDAO
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

		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByOwner(ownerJimmy.get(0));
		assertEquals(6, acquisitionTest.size());
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
		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByDocument(documentTest.get(0));
		assertEquals(3, acquisitionTest.size());
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

		List<Acquisition> acquisitionTest = acquisitionDAO
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
		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByOwner(ownerJimmy.get(0));
		assertEquals(6, acquisitionTest.size());
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
		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByCompetence(competenceTest.get(0));
		assertEquals(3, acquisitionTest.size());
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
		List<Acquisition> acquisitionTest = acquisitionDAO
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
		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByCompetence(competenceTest.get(0));

		assertEquals(2, acquisitionTest.size());
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
		List<Acquisition> acquisitionTest = acquisitionDAO
				.findByDocument(documentTest.get(0));
		assertEquals(1, acquisitionTest.size());
		assertEquals("francois", acquisitionTest.get(0).getOwner()
				.getFirstName());
	}

	@AfterClass
	public static void endTests() {
		PersistenceProvider.removeProvider();
	}

}
