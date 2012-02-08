package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.Document;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DocumentTest {
	
	@Test
	public void testPersist() {
		final Document document = new Document();
		
		document.setCreationDate(new Date(45123645));
		document.setName("Document_secret.txt");
		document.setSum("ergf568g4tr8gh04r5g4f0z4f0zea5f41ze54");
		
		DocumentDAO.create(document);
		
		Document documentTest = DocumentDAO.findById(document.getId());
		
		assertEquals(document.getCreationDate(),documentTest.getCreationDate());
		assertEquals(document.getName(),documentTest.getName());
		assertEquals(document.getSum(),documentTest.getSum());
	}
}
