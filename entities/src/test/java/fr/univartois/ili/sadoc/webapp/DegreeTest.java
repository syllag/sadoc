package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DegreeDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DegreeTest {
	    
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Competence competence = new Competence();
    	final Degree degree = new Degree();
    	
    	ArrayList<Competence> liste = new ArrayList<Competence>();
    	liste.add(competence);
    	degree.setCompetences(liste);    	
    	
    	CompetenceDAO.create(competence);
    	DegreeDAO.create(degree);
    	Competence competenceTest = CompetenceDAO.findById(competence.getId());
    	Degree degreeTest = DegreeDAO.findById(degree.getId());
    	
        assertEquals(degree.getCompetences(),degreeTest.getCompetences());
        assertEquals(degree.getDescription(), degreeTest.getDescription());
        assertEquals(degree.getName(),degreeTest.getName());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
