package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class CompetenceTest {
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
    	final Competence competence = new Competence();
    	final Degree degree = new Degree();
    	ArrayList<Degree> liste = new ArrayList<Degree>();
    	liste.add(degree);
    	competence.setDegrees(liste);    	
    	
    	em.getTransaction().begin();
        em.persist(competence);
        em.persist(degree);
        em.getTransaction().commit();
        
        Competence competenceTest = em.find(Competence.class, competence.getId());
        assertEquals(competence.getDegrees(),competenceTest.getDegrees());
        assertEquals(competence.getDescription(), competenceTest.getDescription());
        assertEquals(competence.getName(),competenceTest.getName());
    }
}
