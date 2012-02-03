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
import fr.univartois.ili.sadoc.entities.Resume;
import fr.univartois.ili.sadoc.entities.User;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class ResumeTest {
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
    	final Resume resume = new Resume();
    	final Competence competence = new Competence();
    	final User user = new User();
    	
    	resume.setUser(user);
    	ArrayList<Competence> liste = new ArrayList<Competence>();
    	liste.add(competence);
    	resume.setCompetences(liste);
    	
    	
    	em.getTransaction().begin();
        em.persist(competence);
        em.persist(user);
        em.persist(resume);
        em.getTransaction().commit();
        
        Resume resumeTest = em.find(Resume.class, resume.getId());
        assertEquals(resume.getCompetences(), resumeTest.getCompetences());
        assertEquals(resume.getUser(), resumeTest.getUser());
    }
}
