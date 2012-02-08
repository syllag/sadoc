package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DegreeDAO;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class CompetenceTest {
	
    @Test
    public void testPersist() throws ParseException {
    	final Competence competence = new Competence();
    	final Degree degree = new Degree();
    	ArrayList<Degree> liste = new ArrayList<Degree>();
    	
    	competence.setDescription("blablabla");
    	competence.setName("toto");
    	
    
    	CompetenceDAO.create(competence);
    	DegreeDAO.create(degree);
    	
    	liste.add(degree);
    	competence.setDegrees(liste);  
    	List<Competence> d= degree.getCompetences();
    	d.add(competence);
    	degree.setCompetences(d);
    	
    	CompetenceDAO.update(competence);
    	DegreeDAO.update(degree);
    	
    	Competence competenceTest = CompetenceDAO.findById(competence.getId());
    	Degree degreeTest = DegreeDAO.findById(degree.getId());
    	
        assertEquals(competence.getDegrees(),competenceTest.getDegrees());
        assertEquals(competence.getDescription(), competenceTest.getDescription());
        assertEquals(competence.getName(),competenceTest.getName());
    }
}
