package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class SignatureTest {

	 @Before
     public void initTests(){
             PersistenceProvider.setProvider("sadocjpatest");
             InitDataForTest.createDataForTest();
     }

	
	@Test
	public void testPersist() {
		final Document document = new Document();
		final Owner owner = new Owner();
		final Competence competence = new Competence();
		final Certificate certificate = new Certificate();
		final Signature signature = new Signature();
		
		signature.setCertificate(certificate);
		signature.setCompetence(competence);
		signature.setDocument(document);
		signature.setOwner(owner);
		
		DocumentDAO.create(document);
		OwnerDAO.create(owner);
		CompetenceDAO.create(competence);
		CertificateDAO.create(certificate);
		SignatureDAO.create(signature);
		
		Signature signatureTest = SignatureDAO.findById(signature.getId());
		
		assertEquals(signature.getCertificate(),signatureTest.getCertificate());
		assertEquals(signature.getCompetence(),signatureTest.getCompetence());
		assertEquals(signature.getDocument(),signatureTest.getDocument());
		assertEquals(signature.getOwner(),signatureTest.getOwner());
	}
	
	@After
    public void endTests(){
            PersistenceProvider.removeProvider();
    }
}
