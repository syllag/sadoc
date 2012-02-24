package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
public class CompetenceTest {
	
	private CompetenceDAO competenceDao =new CompetenceDAO();
	private DegreeDAO degreeDao =new DegreeDAO();
	
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
	}
	 
	
    @Test
    public void testPersist() throws ParseException {
    	final Competence competence = new Competence();
    	final Degree degree = new Degree();
    	ArrayList<Degree> liste = new ArrayList<Degree>();
    	
    	competence.setDescription("blablabla");
    	competence.setName("toto");
    	
    
    	competenceDao.create(competence);
    	degreeDao.create(degree);
    	
    	liste.add(degree);
    	competence.setDegrees(liste);  
    	List<Competence> d= degree.getCompetences();
    	d.add(competence);
    	degree.setCompetences(d);
    	
    	competenceDao.update(competence);
    	degreeDao.update(degree);
    	
    	Competence competenceTest = competenceDao.findById(competence.getId());
    	Degree degreeTest = degreeDao.findById(degree.getId());
    	
        assertEquals(competence.getDegrees(),competenceTest.getDegrees());
        assertEquals(competence.getDescription(), competenceTest.getDescription());
        assertEquals(competence.getName(),competenceTest.getName());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
