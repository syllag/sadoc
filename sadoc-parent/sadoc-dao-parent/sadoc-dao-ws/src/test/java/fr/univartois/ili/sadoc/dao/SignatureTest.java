package fr.univartois.ili.sadoc.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.domaine.Certificate;
import fr.univartois.ili.sadoc.domaine.Competence;
import fr.univartois.ili.sadoc.domaine.Document;
import fr.univartois.ili.sadoc.domaine.Owner;
import fr.univartois.ili.sadoc.domaine.Signature;

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
		final DocumentDAO documentDAO = new DocumentDAO();
		final OwnerDAO ownerDAO = new OwnerDAO();
		final CompetenceDAO competenceDAO = new CompetenceDAO();
		final CertificateDAO certificateDAO = new CertificateDAO();
		final SignatureDAO signatureDAO = new SignatureDAO();
		
		
		signature.setCertificate(certificate);
		signature.setCompetence(competence);
		signature.setDocument(document);
		signature.setOwner(owner);
		
		documentDAO.create(document);
		ownerDAO.create(owner);
		competenceDAO.create(competence);
		certificateDAO.create(certificate);
		signatureDAO.create(signature);
		
		Signature signatureTest = signatureDAO.findById(signature.getId());
		
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
