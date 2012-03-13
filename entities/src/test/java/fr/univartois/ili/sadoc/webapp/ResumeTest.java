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
	   
	
	private OwnerDAO ownerDao ;
	private ResumeDAO resumeDao;
	private CompetenceDAO competenceDao ;
	
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
		ownerDao =new OwnerDAO();
		resumeDao =new ResumeDAO();
		competenceDao =new CompetenceDAO();
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Resume resume = new Resume();
    	final Competence competence = new Competence();
    	final Owner user = new Owner();
    	resume.setOwner(user);
    	competence.setId(4);
    	user.setId(3);
    	ArrayList<Competence> liste = new ArrayList<Competence>();
    	liste.add(competence);
    	resume.setCompetences(liste);
    	
    	competenceDao.create(competence);
    	ownerDao.create(user);
    	resumeDao.create(resume);
    	Resume resumeTest = resumeDao.findById(resume.getId());
    	
        assertEquals(resume.getCompetences(), resumeTest.getCompetences());
        assertEquals(resume.getOwner(), resumeTest.getOwner());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
