package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class AcquisitionTest {
    
	
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Owner user = new Owner();
    	final Document document = new Document();
    	final Competence competence = new Competence();
    	final Acquisition acquisition = new Acquisition();
    	
    	acquisition.setOwner(user);
    	acquisition.setDocument(document);
    	acquisition.setCompetence(competence);
    	
    	OwnerDAO.create(user);
    	DocumentDAO.create(document);
    	CompetenceDAO.create(competence);
    	AcquisitionDAO.create(acquisition);
    	Owner userTest = OwnerDAO.findById(user.getId());
    	Document documentTest = DocumentDAO.findById(document.getId());
    	Competence compenteceTest = CompetenceDAO.findById(competence.getId());
    	Acquisition acquisitionTest = AcquisitionDAO.findById(acquisition.getId());
    	
        assertEquals(acquisition.getCompetence(), acquisitionTest.getCompetence());
        assertEquals(acquisition.getDocument(), acquisitionTest.getDocument());
        assertEquals(acquisition.getOwner(), acquisitionTest.getOwner());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
