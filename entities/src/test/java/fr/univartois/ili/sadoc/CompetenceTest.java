package fr.univartois.ili.sadoc;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entities.Competence;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class CompetenceTest {
	
	@Test
	public void testPersist() {
		final Competence competence = new Competence();
		
		competence.setCreationDate(new Date(454210));
		competence.setDescription("C'est une très belle compétence.");
		competence.setName("Compétence de la mort qui tue.");
		
		CompetenceDAO.create(competence);
		
		Competence competenceTest = CompetenceDAO.findById(competence.getId());
		
		assertEquals(competence.getCreationDate(),competenceTest.getCreationDate());
		assertEquals(competence.getDescription(),competenceTest.getDescription());
		assertEquals(competence.getName(),competenceTest.getName());
	}
}
