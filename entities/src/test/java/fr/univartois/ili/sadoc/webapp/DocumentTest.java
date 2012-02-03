package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.entities.Document;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DocumentTest {
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
    	final Document document = new Document();
    	document.setName("Toto en tutu kaki sur le titi caca");
    	document.setSum("54gr6t8ghz4g84azfaz84d0aef4r0gf4erg56g16044gyj4");
    	
    	em.getTransaction().begin();
        em.persist(document);
        em.getTransaction().commit();
        
        Document documentTest = em.find(Document.class, document.getId());
        assertEquals(document.getName(), documentTest.getName());
        assertEquals(document.getSum(), documentTest.getSum());
    }
}
