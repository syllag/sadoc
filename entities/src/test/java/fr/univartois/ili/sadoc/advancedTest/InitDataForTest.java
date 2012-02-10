package fr.univartois.ili.sadoc.advancedTest;

import java.util.ArrayList;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Signature;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public abstract class InitDataForTest {

        public static void createDataForTest() {
                // Create Owners
                Owner diane = new Owner("diane", "dutartre", "diane.dutartre@jaimal.com");
                Owner francois = new Owner("francois", "guillain", "francois.guillain@jaimal.com");
                Owner jimmy = new Owner("jimmy", "constand", "jimmy.constand@jaimal.com");
                Owner kevin = new Owner("kevin", "pogo", "kevin.pogo@jaimal.com");

                // Create Competences
                Competence competence1 = new Competence("Compétence 1 C2I1",
                                "Première compétence du C2I1");
                Competence competence2 = new Competence("Compétence 2 C2I1",
                                "Deuxième compétence du C2I1");
                Competence competence3 = new Competence("Compétence 3 C2I1",
                                "Troisième compétence du C2I1");
                Competence competence4 = new Competence("Compétence 1 C2I2 et C2I1",
                                "Première compétence du C2I2 et quatrième compétence du C2I1");
                Competence competence5 = new Competence("Compétence 2 C2I2",
                                "Deuxième compétence du C2I2");
                Competence competence6 = new Competence("Compétence 3 C2I2",
                                "Troisième compétence du C2I2");
                Competence competence7 = new Competence(
                                "Compétence semestre 1 licence, C2I1 et C2I2",
                                "Première compétence de la licence et cinquième compétence C2I1, C2I2");
                Competence competence8 = new Competence(
                                "Compétence semestre 2 licence",
                                "Deuxième compétence de la licence");
                Competence competence9 = new Competence(
                                "Compétence semestre 3 licence",
                                "Troisième compétence de la licence");
                Competence competence10 = new Competence(
                                "Compétence semestre 4 licence et master",
                                "Quatrième compétence de la licence, troisième compétence du master");
                Competence competence11 = new Competence(
                                "Compétence semestre 1 master et C2I2",
                                "Première compétence du master et quatrième compétence C2I2");
                Competence competence12 = new Competence(
                                "Compétence semestre 2 master", "Deuxième compétence du master");

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
                
                // Create Certificates
                Certificate certificate1 = new Certificate("ergth", "zeghh");
                Certificate certificate2 = new Certificate("oktrg", "czefi");
                Certificate certificate3 = new Certificate("pogig", "euidj");
                Certificate certificate4 = new Certificate("plzef", "ezffj");
                Certificate certificate5 = new Certificate("jkqsd", "jefoi");
                Certificate certificate6 = new Certificate("ijeff", "ufzfi");
                Certificate certificate7 = new Certificate("okgfj", "eifji");
                Certificate certificate8 = new Certificate("ozekf", "qsdfj");
                
                Signature signature1  = new Signature(document1,francois,competence1,certificate3);
                Signature signature2  = new Signature(document1,francois,competence5,certificate4);
                Signature signature3  = new Signature(document1,francois,competence10,certificate3);
                Signature signature4  = new Signature(document2,francois,competence2,certificate4);
                Signature signature5  = new Signature(document2,francois,competence6,certificate3);
                Signature signature6  = new Signature(document2,francois,competence12,certificate4);
                Signature signature7  = new Signature(document3,diane,competence3,certificate1);
                Signature signature8  = new Signature(document3,diane,competence8,certificate2);
                Signature signature9  = new Signature(document3,diane,competence11,certificate1);
                Signature signature10 = new Signature(document4,jimmy,competence2,certificate5);
                Signature signature11 = new Signature(document4,jimmy,competence4,certificate6);
                Signature signature12 = new Signature(document4,jimmy,competence7,certificate5);
                Signature signature13 = new Signature(document5,kevin,competence3,certificate7);
                Signature signature14 = new Signature(document5,kevin,competence6,certificate8);
                Signature signature15 = new Signature(document5,kevin,competence9,certificate7);
                Signature signature16 = new Signature(document6,diane,competence7,certificate2);
                Signature signature17 = new Signature(document6,diane,competence8,certificate1);
                Signature signature18 = new Signature(document6,diane,competence11,certificate2);
                Signature signature19 = new Signature(document7,kevin,competence10,certificate8);
                Signature signature20 = new Signature(document7,kevin,competence11,certificate7);
                Signature signature21 = new Signature(document7,kevin,competence12,certificate8);
                Signature signature22 = new Signature(document8,jimmy,competence1,certificate6);
                Signature signature23 = new Signature(document8,jimmy,competence6,certificate5);
                Signature signature24 = new Signature(document8,jimmy,competence9,certificate6);
                
                // Persist
                DocumentDAO.create(document1);
                DocumentDAO.create(document2);
                DocumentDAO.create(document3);
                DocumentDAO.create(document4);
                DocumentDAO.create(document5);
                DocumentDAO.create(document6);
                DocumentDAO.create(document7);
                DocumentDAO.create(document8);
                CertificateDAO.create(certificate1);
                CertificateDAO.create(certificate2);
                CertificateDAO.create(certificate3);
                CertificateDAO.create(certificate4);
                CertificateDAO.create(certificate5);
                CertificateDAO.create(certificate6);
                CertificateDAO.create(certificate7);
                CertificateDAO.create(certificate8);
                OwnerDAO.create(diane);
                OwnerDAO.create(francois);
                OwnerDAO.create(jimmy);
                OwnerDAO.create(kevin);
                CompetenceDAO.create(competence1);
                CompetenceDAO.create(competence2);
                CompetenceDAO.create(competence3);
                CompetenceDAO.create(competence4);
                CompetenceDAO.create(competence5);
                CompetenceDAO.create(competence6);
                CompetenceDAO.create(competence7);
                CompetenceDAO.create(competence8);
                CompetenceDAO.create(competence9);
                CompetenceDAO.create(competence10);
                CompetenceDAO.create(competence11);
                CompetenceDAO.create(competence12);
                SignatureDAO.create(signature1);
                SignatureDAO.create(signature2);
                SignatureDAO.create(signature3);
                SignatureDAO.create(signature4);
                SignatureDAO.create(signature5);
                SignatureDAO.create(signature6);
                SignatureDAO.create(signature7);
                SignatureDAO.create(signature8);
                SignatureDAO.create(signature9);
                SignatureDAO.create(signature10);
                SignatureDAO.create(signature11);
                SignatureDAO.create(signature12);
                SignatureDAO.create(signature13);
                SignatureDAO.create(signature14);
                SignatureDAO.create(signature15);
                SignatureDAO.create(signature16);
                SignatureDAO.create(signature17);
                SignatureDAO.create(signature18);
                SignatureDAO.create(signature19);
                SignatureDAO.create(signature20);
                SignatureDAO.create(signature21);
                SignatureDAO.create(signature22);
                SignatureDAO.create(signature23);
                SignatureDAO.create(signature24);
                
                ArrayList<Certificate> liste1 = new ArrayList<Certificate>();
                liste1.add(certificate1);liste1.add(certificate2);
                diane.setCertificates(liste1);
                ArrayList<Certificate> liste2 = new ArrayList<Certificate>();
                liste2.add(certificate3);liste2.add(certificate4);
                francois.setCertificates(liste2);
                ArrayList<Certificate> liste3 = new ArrayList<Certificate>();
                liste3.add(certificate5);liste3.add(certificate6);
                diane.setCertificates(liste3);
                ArrayList<Certificate> liste4 = new ArrayList<Certificate>();
                liste4.add(certificate7);liste4.add(certificate8);
                diane.setCertificates(liste4);
                
                CertificateDAO.update(certificate1);
                CertificateDAO.update(certificate2);
                CertificateDAO.update(certificate3);
                CertificateDAO.update(certificate4);
                CertificateDAO.update(certificate5);
                CertificateDAO.update(certificate6);
                CertificateDAO.update(certificate7);
                CertificateDAO.update(certificate8);
                OwnerDAO.update(diane);
                OwnerDAO.update(francois);
                OwnerDAO.update(jimmy);
                OwnerDAO.update(kevin);
        }
}