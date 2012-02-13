package fr.univartois.ili.sadoc.webapp.AvancedTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DegreeDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.ResumeDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Resume;

public class InitDataForTest {

	public InitDataForTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createDataForTest() {
		// create owner test
		Owner diane = new Owner("diane", "dutartre",
				"diane.dutartre@jaimal.com", "dianeDutartre",
				"40 rue des perches", "62880", "Lens", "0101010101");
		Owner francois = new Owner("francois", "guillain",
				"francois.guillain@jaimal.com", "francoisGuillain",
				"20 rue des pigeons", "62000", "arras", "0202020202");
		Owner jimmy = new Owner("jimmy", "constand",
				"jimmy.constand@jaimal.com", "jimmyConstand",
				"10 rue des champignons", "59000", "lille", "0303030303");
		Owner kevin = new Owner("kevin", "pogo", "kevin.pogo@jaimal.com",
				"kevinPogo", "30 rue des alouettes", "62330", "Meurchin",
				"0404040404");
		
		// create user in database	
		OwnerDAO.create(diane);
		OwnerDAO.create(francois);
		OwnerDAO.create(jimmy);
		OwnerDAO.create(kevin);
		

		// create competence
		Competence competence1 = new Competence("competence 1 C2I1",
				"premier competence du C2I1");
		Competence competence2 = new Competence("competence 2 C2I1",
				"deuxiéme competence du C2I1");
		Competence competence3 = new Competence("competence 3 C2I1",
				"troisiéme competence du C2I1");
		Competence competence4 = new Competence("competence 1 C2I2 et C2I1",
				"premier competence du C2I2 et quatriéme competence du C2I1");
		Competence competence5 = new Competence("competence 2 C2I2",
				"deuxiéme competence du C2I2");
		Competence competence6 = new Competence("competence 3 C2I2",
				"troisiéme competence du C2I2");
		Competence competence7 = new Competence(
				"competence semestre 1 licence, C2I1 et C2I2",
				"premier competence de la licence et cinquiéme competence C2I1, C2I2");
		Competence competence8 = new Competence(
				"competence semestre 2 licence",
				"deuxiéme competence de la licence");
		Competence competence9 = new Competence(
				"competence semestre 3 licence",
				"troisiéme competence de la licence");
		Competence competence10 = new Competence(
				"competence semestre 4 licence et master",
				"quatriéme competence de la licence,troisiéme competence du master");
		Competence competence11 = new Competence(
				"competence semestre 1 master et C2I2",
				"premier competence du master et quatriéme competence C2I2");
		Competence competence12 = new Competence(
				"competence semestre 2 master", "deuxiéme competence du master");
		
		//create competence in database
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

		// create degrees
		Degree c2i1 = new Degree("C2I1", "diplôme du C2I 1");
		Degree c2i2 = new Degree("C2I2", "diplôme du C2I 2");
		Degree licence = new Degree("licence", "diplôme de la lience");
		Degree master = new Degree("master", "diplôme du master");
		
		//create degree in database
		DegreeDAO.create(c2i1);
		DegreeDAO.create(c2i2);
		DegreeDAO.create(licence);
		DegreeDAO.create(master);
		
		// board byte for file
		byte[] pk7 = new byte[2];
		pk7[0] = 0;
		pk7[1] = 1;

		// create document
		Document document1 = new Document("document1", "azertyuiop",
				"www.document1.fr", pk7, new Date(2012, 2, 10));
		Document document2 = new Document("document2", "qsdfghjklm",
				"www.document2.fr", pk7, new Date(2011, 4, 17));
		Document document3 = new Document("document3", "wxcvbnazerty",
				"www.document3.fr", pk7, new Date(2010, 8, 14));
		Document document4 = new Document("document4", "aqwxszedcvfr",
				"www.document4.fr", pk7, new Date(2009, 7, 03));
		Document document5 = new Document("document5", "rfvbgtyhn",
				"www.document5.fr", pk7, new Date(2009, 7, 03));
		Document document6 = new Document("document6", "rfvbgtyhn",
				"www.document6.fr", pk7, new Date(2009, 6, 03));
		Document document7 = new Document("document7", "rfvbgtyhn",
				"www.document7.fr", pk7, new Date(2009, 5, 03));
		Document document8 = new Document("document8", "rfvbgtyhn",
				"www.document8.fr", pk7, new Date(2009, 4, 03));
		
		//create document in database
		DocumentDAO.create(document1);
		DocumentDAO.create(document2);
		DocumentDAO.create(document3);
		DocumentDAO.create(document4);
		DocumentDAO.create(document5);
		DocumentDAO.create(document6);
		DocumentDAO.create(document7);
		DocumentDAO.create(document8);
		
		// //link degree/////
		// c2i1
		List<Competence> listCompetenceC2I1 = new ArrayList<Competence>();
		listCompetenceC2I1.add(competence1);
		listCompetenceC2I1.add(competence2);
		listCompetenceC2I1.add(competence3);
		listCompetenceC2I1.add(competence4);
		listCompetenceC2I1.add(competence7);

		c2i1.setCompetences(listCompetenceC2I1);
		
		DegreeDAO.update(c2i1);

		// c2i2
		List<Competence> listCompetenceC2I2 = new ArrayList<Competence>();
		listCompetenceC2I2.add(competence4);
		listCompetenceC2I2.add(competence5);
		listCompetenceC2I2.add(competence6);
		listCompetenceC2I2.add(competence7);
		listCompetenceC2I2.add(competence11);

		c2i2.setCompetences(listCompetenceC2I2);
		
		DegreeDAO.update(c2i2);

		// licence
		List<Competence> listCompetenceLicence = new ArrayList<Competence>();
		listCompetenceLicence.add(competence7);
		listCompetenceLicence.add(competence8);
		listCompetenceLicence.add(competence9);
		listCompetenceLicence.add(competence10);

		licence.setCompetences(listCompetenceLicence);
		
		DegreeDAO.update(licence);

		// master
		List<Competence> listCompetenceMaster = new ArrayList<Competence>();
		listCompetenceMaster.add(competence10);
		listCompetenceMaster.add(competence11);
		listCompetenceMaster.add(competence12);

		master.setCompetences(listCompetenceMaster);
		
		DegreeDAO.update(master);

		// // link competence ////
		// competence 1
		List<Degree> listDegreeCompetence1 = new ArrayList<Degree>();
		listDegreeCompetence1.add(c2i1);

		competence1.setDegrees(listDegreeCompetence1);
		
		CompetenceDAO.update(competence1);

		// competence 2
		List<Degree> listDegreeCompetence2 = new ArrayList<Degree>();
		listDegreeCompetence2.add(c2i1);

		competence2.setDegrees(listDegreeCompetence2);
		
		CompetenceDAO.update(competence2);

		// competence 3
		List<Degree> listDegreeCompetence3 = new ArrayList<Degree>();
		listDegreeCompetence3.add(c2i1);

		competence3.setDegrees(listDegreeCompetence3);
		
		CompetenceDAO.update(competence3);

		// competence 4
		List<Degree> listDegreeCompetence4 = new ArrayList<Degree>();
		listDegreeCompetence4.add(c2i1);
		listDegreeCompetence4.add(c2i2);

		competence4.setDegrees(listDegreeCompetence4);
		
		CompetenceDAO.update(competence4);

		// competence 5
		List<Degree> listDegreeCompetence5 = new ArrayList<Degree>();
		listDegreeCompetence5.add(c2i2);

		competence5.setDegrees(listDegreeCompetence5);
		
		CompetenceDAO.update(competence5);

		// competence 6
		List<Degree> listDegreeCompetence6 = new ArrayList<Degree>();
		listDegreeCompetence6.add(c2i2);

		competence6.setDegrees(listDegreeCompetence6);
		
		CompetenceDAO.update(competence6);

		// competence 7
		List<Degree> listDegreeCompetence7 = new ArrayList<Degree>();
		listDegreeCompetence7.add(licence);
		listDegreeCompetence7.add(c2i1);
		listDegreeCompetence7.add(c2i2);

		competence7.setDegrees(listDegreeCompetence7);
		
		CompetenceDAO.update(competence7);

		// competence 8
		List<Degree> listDegreeCompetence8 = new ArrayList<Degree>();
		listDegreeCompetence8.add(licence);

		competence8.setDegrees(listDegreeCompetence8);
		
		CompetenceDAO.update(competence8);

		// competence 9
		List<Degree> listDegreeCompetence9 = new ArrayList<Degree>();
		listDegreeCompetence9.add(licence);

		competence9.setDegrees(listDegreeCompetence9);
		
		CompetenceDAO.update(competence9);

		// competence 10
		List<Degree> listDegreeCompetence10 = new ArrayList<Degree>();
		listDegreeCompetence10.add(licence);
		listDegreeCompetence10.add(master);

		competence10.setDegrees(listDegreeCompetence10);
		
		CompetenceDAO.update(competence10);

		// competence 11
		List<Degree> listDegreeCompetence11 = new ArrayList<Degree>();
		listDegreeCompetence11.add(master);
		listDegreeCompetence11.add(c2i2);

		competence11.setDegrees(listDegreeCompetence11);
		
		CompetenceDAO.update(competence11);

		// competence 12
		List<Degree> listDegreeCompetence12 = new ArrayList<Degree>();
		listDegreeCompetence12.add(master);

		competence12.setDegrees(listDegreeCompetence12);
		
		CompetenceDAO.update(competence12);

		// link resume
		// resume diane
		Resume resumeDiane = new Resume(diane, null);
		
		ResumeDAO.create(resumeDiane);

		// resume francois
		List<Competence> listCompetenceFrancois = new ArrayList<Competence>();
		listCompetenceFrancois.add(competence1);
		listCompetenceFrancois.add(competence2);
		listCompetenceFrancois.add(competence10);
		listCompetenceFrancois.add(competence12);

		Resume resumeFrancois = new Resume(francois, listCompetenceFrancois);
		
		ResumeDAO.create(resumeFrancois);

		// resume jimmy
		List<Competence> listCompetenceJimmy = new ArrayList<Competence>();
		listCompetenceJimmy.add(competence7);
		listCompetenceJimmy.add(competence8);
		listCompetenceJimmy.add(competence9);
		listCompetenceJimmy.add(competence1);
		listCompetenceJimmy.add(competence10);
		listCompetenceJimmy.add(competence6);

		Resume resumeJimmy = new Resume(jimmy, listCompetenceJimmy);
		
		ResumeDAO.create(resumeJimmy);

		// resume kevin
		List<Competence> listCompetenceKevin = new ArrayList<Competence>();
		listCompetenceKevin.add(competence10);
		listCompetenceKevin.add(competence11);
		listCompetenceKevin.add(competence1);
		listCompetenceKevin.add(competence2);
		listCompetenceKevin.add(competence3);
		listCompetenceKevin.add(competence4);
		listCompetenceKevin.add(competence8);
		listCompetenceKevin.add(competence7);

		Resume resumeKevin = new Resume(kevin, listCompetenceKevin);
		
		ResumeDAO.create(resumeKevin);

		// resume kevin
		List<Competence> listCompetenceKevin1 = new ArrayList<Competence>();
		listCompetenceKevin1.add(competence10);
		listCompetenceKevin1.add(competence11);
		listCompetenceKevin1.add(competence12);

		Resume resumeKevin1 = new Resume(kevin, listCompetenceKevin1);
		
		ResumeDAO.create(resumeKevin1);

		// resume kevin
		List<Competence> listCompetenceKevin2 = new ArrayList<Competence>();
		listCompetenceKevin2.add(competence10);
		listCompetenceKevin2.add(competence11);
		listCompetenceKevin2.add(competence1);
		listCompetenceKevin2.add(competence2);
		listCompetenceKevin2.add(competence3);
		listCompetenceKevin2.add(competence4);
		listCompetenceKevin2.add(competence7);

		Resume resumeKevin2 = new Resume(kevin, listCompetenceKevin2);
		
		ResumeDAO.create(resumeKevin2);

		// link owner
		// owner diane
		diane.setDegrees(null);
		diane.setResumes(null);
		
		OwnerDAO.update(diane);

		// owner françois
		francois.setDegrees(null);
		List<Resume> listResumeFrancoisList = new ArrayList<Resume>();
		listResumeFrancoisList.add(resumeFrancois);
		francois.setResumes(listResumeFrancoisList);
		
		OwnerDAO.update(francois);

		// owner jimmy
		List<Degree> listDegreesJimmy = new ArrayList<Degree>();
		listDegreesJimmy.add(licence);
		jimmy.setDegrees(listDegreesJimmy);
		List<Resume> listResumeJimmyList = new ArrayList<Resume>();
		listResumeJimmyList.add(resumeJimmy);
		jimmy.setResumes(listResumeJimmyList);
		
		OwnerDAO.update(jimmy);

		// owner Kevin
		List<Degree> listDegreesKevin = new ArrayList<Degree>();
		listDegreesKevin.add(c2i1);
		listDegreesKevin.add(master);
		kevin.setDegrees(listDegreesKevin);
		List<Resume> listResumeKevinList = new ArrayList<Resume>();
		listResumeKevinList.add(resumeKevin);
		listResumeKevinList.add(resumeKevin1);
		listResumeKevinList.add(resumeKevin2);
		kevin.setResumes(listResumeKevinList);
		
		OwnerDAO.update(kevin);

		// link Acquisition
		// acquisition diane
		Acquisition acquisition = new Acquisition(diane,null, null,new Date(2012,02,02));
		
		AcquisitionDAO.create(acquisition);
		
		// acquisition francois
		Acquisition acquisition1 = new Acquisition(francois,document1, competence1,new Date(2012, 2, 10));
		Acquisition acquisition2 = new Acquisition(francois,document1, competence2,new Date(2012, 2, 10));
		Acquisition acquisition3 = new Acquisition(francois,document2, competence10,new Date(2012, 2, 10));
		Acquisition acquisition4 = new Acquisition(francois,document1, competence12,new Date(2012, 2, 10));
		
		AcquisitionDAO.create(acquisition1);
		AcquisitionDAO.create(acquisition2);
		AcquisitionDAO.create(acquisition3);
		AcquisitionDAO.create(acquisition4);
		
		// acquisition jimmy
		Acquisition acquisition5 = new Acquisition(jimmy,document3, competence7, new Date(2010, 8, 14));
		Acquisition acquisition6 = new Acquisition(jimmy,document3, competence8, new Date(2010, 8, 14));
		Acquisition acquisition7 = new Acquisition(jimmy,document3, competence9, new Date(2010, 8, 14));
		Acquisition acquisition8 = new Acquisition(jimmy,document4, competence1, new Date(2009, 7, 03));
		Acquisition acquisition9 = new Acquisition(jimmy,document5, competence10, new Date(2009, 7, 03));
		Acquisition acquisition10 = new Acquisition(jimmy,document5, competence6, new Date(2009, 7, 03));
		
		AcquisitionDAO.create(acquisition5);
		AcquisitionDAO.create(acquisition6);
		AcquisitionDAO.create(acquisition7);
		AcquisitionDAO.create(acquisition8);
		AcquisitionDAO.create(acquisition9);
		AcquisitionDAO.create(acquisition10);
		
		//acquisition Kevin
		Acquisition acquisition11 = new Acquisition(kevin,document6, competence10, new Date(2009, 6, 03));
		Acquisition acquisition12 = new Acquisition(kevin,document6, competence11, new Date(2009, 6, 03));
		Acquisition acquisition13 = new Acquisition(kevin,document7, competence1, new Date(2009, 5, 03));
		Acquisition acquisition14 = new Acquisition(kevin,document7, competence2, new Date(2009, 5, 03));
		Acquisition acquisition15 = new Acquisition(kevin,document7, competence3, new Date(2009, 5, 03));
		Acquisition acquisition16 = new Acquisition(kevin,document8, competence4, new Date(2009, 4, 03));
		Acquisition acquisition17 = new Acquisition(kevin,document8, competence8, new Date(2009, 4, 03));
		Acquisition acquisition18 = new Acquisition(kevin,document8, competence7, new Date(2009, 4, 03));
		
		AcquisitionDAO.create(acquisition11);
		AcquisitionDAO.create(acquisition12);
		AcquisitionDAO.create(acquisition13);
		AcquisitionDAO.create(acquisition14);
		AcquisitionDAO.create(acquisition15);
		AcquisitionDAO.create(acquisition16);
		AcquisitionDAO.create(acquisition17);
		AcquisitionDAO.create(acquisition18);
		
	}
}
