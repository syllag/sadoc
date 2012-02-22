package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

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
		
		document.setCreationDate(new Date(45123645));
		document.setName("Document_secret.txt");
		document.setCheckSum("ergf568g4tr8gh04r5g4f0z4f0zea5f41ze54");
		byte[] tab = new byte[3];
		tab[0]=1;tab[1]=2;tab[2]=3;
		document.setPk7(tab);
		
		DocumentDAO.create(document);
		
		Document documentTest = DocumentDAO.findById(document.getId());
		
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
