package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.dao.ResumeDAO;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Resume;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class ResumeTest {
	   
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Resume resume = new Resume();
    	final Competence competence = new Competence();
    	final Owner user = new Owner();
    	
    	resume.setOwner(user);
    	ArrayList<Competence> liste = new ArrayList<Competence>();
    	liste.add(competence);
    	resume.setCompetences(liste);
    	
    	CompetenceDAO.create(competence);
    	OwnerDAO.create(user);
    	ResumeDAO.create(resume);
    	Owner userTest = OwnerDAO.findById(user.getId());
    	Competence competenceTest = CompetenceDAO.findById(competence.getId());
    	Resume resumeTest = ResumeDAO.findById(resume.getId());
    	
        assertEquals(resume.getCompetences(), resumeTest.getCompetences());
        assertEquals(resume.getOwner(), resumeTest.getOwner());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
