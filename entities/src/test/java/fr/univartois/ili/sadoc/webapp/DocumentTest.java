package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Document;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DocumentTest {
	    

	private DocumentDAO documentDao;
	
	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
		documentDao =new DocumentDAO();
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Document document = new Document();
    	document.setName("Toto en tutu kaki sur le titi caca");
    	document.setCheckSum("54gr6t8ghz4g84azfaz84d0aef4r0gf4erg56g16044gyj4");
    	
    	documentDao.create(document);
    	Document documentTest = documentDao.findById(document.getId());
    	
        assertEquals(document.getName(), documentTest.getName());
        assertEquals(document.getCheckSum(), documentTest.getCheckSum());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
