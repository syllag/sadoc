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
    
	
	private OwnerDAO ownerDao;
	private DocumentDAO documentDao ;
	private CompetenceDAO competenceDao ;
	private AcquisitionDAO acquisitionDao ;
	
	
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
		ownerDao =new OwnerDAO();
		documentDao =new DocumentDAO();
		competenceDao =new CompetenceDAO();
		acquisitionDao =new AcquisitionDAO();
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Owner owner = new Owner();
    	final Document document = new Document();
    	final Competence competence = new Competence();
    	final Acquisition acquisition = new Acquisition();
    	owner.setId(1);
    	document.setId(1);
    	competence.setId(1);
    	acquisition.setOwner(owner);
    	acquisition.setDocument(document);
    	acquisition.setCompetence(competence);
    	
    	ownerDao.create(owner);
    	documentDao.create(document);
    	competenceDao.create(competence);
    	acquisitionDao.create(acquisition);
    	Acquisition acquisitionTest = acquisitionDao.findById(acquisition.getId());
    	
        assertEquals(acquisition.getCompetence(), acquisitionTest.getCompetence());
        assertEquals(acquisition.getDocument(), acquisitionTest.getDocument());
        assertEquals(acquisition.getOwner(), acquisitionTest.getOwner());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
