package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class AcquisitionTest {
	private EntityManager em;

    @Before
    public void setUp() {
        EntityManagerFactory fact = Persistence.createEntityManagerFactory("TestPU");
        em = fact.createEntityManager();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testPersist() throws ParseException {
    	final Owner user = new Owner();
    	final Document document = new Document();
    	final Competence competence = new Competence();
    	final Acquisition acquisition = new Acquisition();
    	
    	acquisition.setUser(user);
    	acquisition.setDocument(document);
    	acquisition.setCompetence(competence);
    	
    	em.getTransaction().begin();
        em.persist(user);
        em.persist(document);
        em.persist(competence);
        em.getTransaction().commit();
        
        Acquisition acquisitionTest = em.find(Acquisition.class, acquisition.getId());
        assertEquals(acquisition.getCompetence(), acquisitionTest.getCompetence());
        assertEquals(acquisition.getDocument(), acquisitionTest.getDocument());
        assertEquals(acquisition.getUser(), acquisitionTest.getUser());
    }
}
