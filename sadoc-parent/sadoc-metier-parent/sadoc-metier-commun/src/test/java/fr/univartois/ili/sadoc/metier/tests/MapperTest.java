package fr.univartois.ili.sadoc.metier.tests;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;
import fr.univartois.ili.sadoc.metier.commun.utils.Mapper;


public class MapperTest {
	
	private ApplicationContext ac;
	private IReferentielDAO ref;
	private ICompetenceDAO comp;
	private IItemDAO item;
	private IDomaineDAO dom;

	@Before
	public void setUp(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		ref=(IReferentielDAO)ac.getBean("referentielDAO");
		comp=(ICompetenceDAO)ac.getBean("competenceDAO");
		item=(IItemDAO)ac.getBean("itemDAO");
		dom=(IDomaineDAO)ac.getBean("domaineDAO");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetCompetenceFromEntityParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getCompetenceFromEntity(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetItemFromEntityParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getItemFromEntity(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetDomainFromEntityParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getDomainFromEntity(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetReferentielFromEntityParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getReferentielFromEntity(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetCompetenceFromDAOParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getCompetenceFromVO(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetItemFromDAOParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getItemFromVO(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetDomainFromDAOParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getDomainFromVO(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIfGetReferentielFromDAOParameterIsNull() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Mapper.getReferentielFromVO(null);
	}
	
	@Test
	public void testGetCompetenceFromEntity(){
		
	}
	
	@Test
	public void testGetCompetenceFromDAO(){
		
	}
	
	@Test
	public void testGetDomainFromEntity(){
		
	}
	
	@Test
	public void testGetDomainFromDAO(){
		
	}
	
	@Test
	public void testGetItemFromEntity(){
		
	}
	
	@Test
	public void testGetItemFromDAO(){
		
	}
	
	@Ignore
	public void testGetReferentielFromEntity(){
		Referentiel r=ref.findReferentielById(1);
		assertEquals(r.getId(),1);
		fr.univartois.ili.sadoc.metier.commun.vo.Referentiel r2=Mapper.getReferentielFromEntity(r);
		assertEquals(r2.getId(), 1);
	}
	
	@Test
	public void testGetReferentielFromDAO(){
		
	}
	

}
