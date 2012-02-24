package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class DocumentTest {
	
	@Before
    public void initTests(){
            PersistenceProvider.setProvider("sadocjpatest");
            InitDataForTest.createDataForTest();
    }
	
	@Test
	public void testPersist() {
		final Document document = new Document();
		final DocumentDAO documentDAO = new DocumentDAO();
		
		document.setCreationDate(new Date(45123645));
		document.setName("Document_secret.txt");
		document.setCheckSum("ergf568g4tr8gh04r5g4f0z4f0zea5f41ze54");
		byte[] tab = new byte[2];
		tab[0]=0;tab[1]=1;
		document.setPk7(tab);
		
		documentDAO.create(document);
		
		Document documentTest = documentDAO.findById(document.getId());
		
		assertEquals(document.getCreationDate(),documentTest.getCreationDate());
		assertEquals(document.getName(),documentTest.getName());
		assertEquals(document.getCheckSum(),documentTest.getCheckSum());
		assertEquals(document.getPk7(),documentTest.getPk7());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }
}
