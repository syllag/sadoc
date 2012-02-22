//package fr.univartois.ili.sadoc;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Date;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import fr.univartois.ili.sadoc.advancedTest.InitDataForTest;
//import fr.univartois.ili.sadoc.entities.classes.Competence;
//import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;
//import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
//
///**
// * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
// * 
// */
//public class CompetenceTest {
//	
//	 @Before
//     public void initTests(){
//             PersistenceProvider.setProvider("sadocjpatest");
//             InitDataForTest.createDataForTest();
//     }
//
//	
//	@Test
//	public void testPersist() {
//		final Competence competence = new Competence();
//		
//		competence.setCreationDate(new Date(454210));
//		competence.setDescription("C'est une très belle compétence.");
//		competence.setName("Compétence de la mort qui tue.");
//		
//		CompetenceDAO.create(competence);
//		
//		Competence competenceTest = CompetenceDAO.findById(competence.getId());
//		
//		assertEquals(competence.getCreationDate(),competenceTest.getCreationDate());
//		assertEquals(competence.getDescription(),competenceTest.getDescription());
//		assertEquals(competence.getName(),competenceTest.getName());
//	}
//	
//	@After
//    public void endTests(){
//            PersistenceProvider.removeProvider();
//    }
//}
