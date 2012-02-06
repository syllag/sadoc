package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DocumentTest {
	    
    @Test
    public void testPersist() throws ParseException {
    	final Document document = new Document();
    	document.setName("Toto en tutu kaki sur le titi caca");
    	document.setSum("54gr6t8ghz4g84azfaz84d0aef4r0gf4erg56g16044gyj4");
    	
    	DocumentDAO.create(document);
    	Document documentTest = DocumentDAO.findById(document.getId());
    	
        assertEquals(document.getName(), documentTest.getName());
        assertEquals(document.getSum(), documentTest.getSum());
    }
}
