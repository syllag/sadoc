package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
	
	@Before
    public void initTests(){
            PersistenceProvider.setProvider("sadocjpatest");
    }
	
	@Test
	public void testPersist() {
		final Document document = new Document();
		
		document.setCreationDate(new Date(45123645));
		document.setName("Document_secret.txt");
		document.setCheckSum("ergf568g4tr8gh04r5g4f0z4f0zea5f41ze54");
		
		DocumentDAO.create(document);
		
		Document documentTest = DocumentDAO.findById(document.getId());
		
		assertEquals(document.getCreationDate(),documentTest.getCreationDate());
		assertEquals(document.getName(),documentTest.getName());
		assertEquals(document.getCheckSum(),documentTest.getCheckSum());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }
}
