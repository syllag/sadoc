package fr.univartois.ili.sadoc.dao;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.domaine.Certificate;
import fr.univartois.ili.sadoc.domaine.Competence;
import fr.univartois.ili.sadoc.domaine.Document;
import fr.univartois.ili.sadoc.domaine.Owner;
import fr.univartois.ili.sadoc.domaine.Signature;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public abstract class InitDataForTest {

	public static void createDataForTest() {
		PersistenceProvider.setProvider("sadocjpatest");
		final EntityManager entityManager = PersistenceProvider.getEntityManager();
		final DocumentDAO documentDAO = new DocumentDAO();
		final OwnerDAO ownerDAO = new OwnerDAO();
		final CertificateDAO certificateDAO = new CertificateDAO();
		final CompetenceDAO competenceDAO = new CompetenceDAO();
		final SignatureDAO signatureDAO = new SignatureDAO();

		// Create Owners
		Owner diane = new Owner("diane", "dutartre",
				"diane.dutartre@jaimal.com");
		Owner francois = new Owner("francois", "guillain",
				"francois.guillain@jaimal.com");
		Owner jimmy = new Owner("jimmy", "constand",
				"jimmy.constand@jaimal.com");
		Owner kevin = new Owner("kevin", "pogo", "kevin.pogo@jaimal.com");

		// Create Competences
		Competence competence1 = new Competence("competence01",
				"Première compétence du C2I1");
		Competence competence2 = new Competence("competence02",
				"Deuxième compétence du C2I1");
		Competence competence3 = new Competence("competence03",
				"Troisième compétence du C2I1");
		Competence competence4 = new Competence("competence04",
				"Première compétence du C2I2 et quatrième compétence du C2I1");
		Competence competence5 = new Competence("competence05",
				"Deuxième compétence du C2I2");
		Competence competence6 = new Competence("competence06",
				"Troisième compétence du C2I2");
		Competence competence7 = new Competence("competence07",
				"Première compétence de la licence et cinquième compétence C2I1, C2I2");
		Competence competence8 = new Competence("competence08",
				"Deuxième compétence de la licence");
		Competence competence9 = new Competence("competence09",
				"Troisième compétence de la licence");
		Competence competence10 = new Competence("competence10",
				"Quatrième compétence de la licence, troisième compétence du master");
		Competence competence11 = new Competence("competence11",
				"Première compétence du master et quatrième compétence C2I2");
		Competence competence12 = new Competence("competence12",
				"Deuxième compétence du master");

		// Board byte for file
		byte[] pk7 = new byte[2];
		pk7[0] = 0;
		pk7[1] = 1;

		// Create Documents
		Document document1 = new Document("document1", "azertyuiop", pk7);
		Document document2 = new Document("document2", "qsdfghjklm", pk7);
		Document document3 = new Document("document3", "wxcvbnazerty", pk7);
		Document document4 = new Document("document4", "aqwxszedcvfr", pk7);
		Document document5 = new Document("document5", "rfvbgtyhn", pk7);
		Document document6 = new Document("document6", "rfvbgtyhn", pk7);
		Document document7 = new Document("document7", "rfvbgtyhn", pk7);
		Document document8 = new Document("document8", "rfvbgtyhn", pk7);

		KeyPairGenerator ke2 = null;
		try {
			ke2 = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		ke2.initialize(1024, new SecureRandom());

		KeyPair k2 = ke2.generateKeyPair();
		// Create Certificates
		Certificate certificate1 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate2 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate3 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate4 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate5 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate6 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate7 = new Certificate(k2.getPublic(),
				k2.getPrivate());
		Certificate certificate8 = new Certificate(k2.getPublic(),
				k2.getPrivate());

		Signature signature1 = new Signature(document1, francois, competence1,
				certificate3);
		Signature signature2 = new Signature(document1, francois, competence5,
				certificate4);
		Signature signature3 = new Signature(document1, francois, competence10,
				certificate3);
		Signature signature4 = new Signature(document2, francois, competence2,
				certificate4);
		Signature signature5 = new Signature(document2, francois, competence6,
				certificate3);
		Signature signature6 = new Signature(document2, francois, competence12,
				certificate4);
		Signature signature7 = new Signature(document3, diane, competence3,
				certificate1);
		Signature signature8 = new Signature(document3, diane, competence8,
				certificate2);
		Signature signature9 = new Signature(document3, diane, competence11,
				certificate1);
		Signature signature10 = new Signature(document4, jimmy, competence2,
				certificate5);
		Signature signature11 = new Signature(document4, jimmy, competence4,
				certificate6);
		Signature signature12 = new Signature(document4, jimmy, competence7,
				certificate5);
		Signature signature13 = new Signature(document5, kevin, competence3,
				certificate7);
		Signature signature14 = new Signature(document5, kevin, competence6,
				certificate8);
		Signature signature15 = new Signature(document5, kevin, competence9,
				certificate7);
		Signature signature16 = new Signature(document6, diane, competence7,
				certificate2);
		Signature signature17 = new Signature(document6, diane, competence8,
				certificate1);
		Signature signature18 = new Signature(document6, diane, competence11,
				certificate2);
		Signature signature19 = new Signature(document7, kevin, competence10,
				certificate8);
		Signature signature20 = new Signature(document7, kevin, competence11,
				certificate7);
		Signature signature21 = new Signature(document7, kevin, competence12,
				certificate8);
		Signature signature22 = new Signature(document8, jimmy, competence1,
				certificate6);
		Signature signature23 = new Signature(document8, jimmy, competence6,
				certificate5);
		Signature signature24 = new Signature(document8, jimmy, competence9,
				certificate6);
		
		entityManager.getTransaction().begin();
		
		documentDAO.create(document1);
		documentDAO.create(document2);
		documentDAO.create(document3);
		documentDAO.create(document4);
		documentDAO.create(document5);
		documentDAO.create(document6);
		documentDAO.create(document7);
		documentDAO.create(document8);
		certificateDAO.create(certificate1);
		certificateDAO.create(certificate2);
		certificateDAO.create(certificate3);
		certificateDAO.create(certificate4);
		certificateDAO.create(certificate5);
		certificateDAO.create(certificate6);
		certificateDAO.create(certificate7);
		certificateDAO.create(certificate8);
		ownerDAO.create(diane);
		ownerDAO.create(francois);
		ownerDAO.create(jimmy);
		ownerDAO.create(kevin);
		competenceDAO.create(competence1);
		competenceDAO.create(competence2);
		competenceDAO.create(competence3);
		competenceDAO.create(competence4);
		competenceDAO.create(competence5);
		competenceDAO.create(competence6);
		competenceDAO.create(competence7);
		competenceDAO.create(competence8);
		competenceDAO.create(competence9);
		competenceDAO.create(competence10);
		competenceDAO.create(competence11);
		competenceDAO.create(competence12);
		signatureDAO.create(signature1);
		signatureDAO.create(signature2);
		signatureDAO.create(signature3);
		signatureDAO.create(signature4);
		signatureDAO.create(signature5);
		signatureDAO.create(signature6);
		signatureDAO.create(signature7);
		signatureDAO.create(signature8);
		signatureDAO.create(signature9);
		signatureDAO.create(signature10);
		signatureDAO.create(signature11);
		signatureDAO.create(signature12);
		signatureDAO.create(signature13);
		signatureDAO.create(signature14);
		signatureDAO.create(signature15);
		signatureDAO.create(signature16);
		signatureDAO.create(signature17);
		signatureDAO.create(signature18);
		signatureDAO.create(signature19);
		signatureDAO.create(signature20);
		signatureDAO.create(signature21);
		signatureDAO.create(signature22);
		signatureDAO.create(signature23);
		signatureDAO.create(signature24);

		ArrayList<Certificate> liste1 = new ArrayList<Certificate>();
		liste1.add(certificate1);
		liste1.add(certificate2);
		diane.setCertificates(liste1);
		ArrayList<Certificate> liste2 = new ArrayList<Certificate>();
		liste2.add(certificate3);
		liste2.add(certificate4);
		francois.setCertificates(liste2);
		ArrayList<Certificate> liste3 = new ArrayList<Certificate>();
		liste3.add(certificate5);
		liste3.add(certificate6);
		diane.setCertificates(liste3);
		ArrayList<Certificate> liste4 = new ArrayList<Certificate>();
		liste4.add(certificate7);
		liste4.add(certificate8);
		diane.setCertificates(liste4);

		certificateDAO.update(certificate1);
		certificateDAO.update(certificate2);
		certificateDAO.update(certificate3);
		certificateDAO.update(certificate4);
		certificateDAO.update(certificate5);
		certificateDAO.update(certificate6);
		certificateDAO.update(certificate7);
		certificateDAO.update(certificate8);
		ownerDAO.update(diane);
		ownerDAO.update(francois);
		ownerDAO.update(jimmy);
		ownerDAO.update(kevin);
		entityManager.getTransaction().commit();
	}
}