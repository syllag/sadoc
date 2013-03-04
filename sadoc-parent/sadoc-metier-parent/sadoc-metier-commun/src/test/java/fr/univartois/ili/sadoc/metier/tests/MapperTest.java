package fr.univartois.ili.sadoc.metier.tests;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import fr.univartois.ili.sadoc.metier.commun.utils.Mapper;


public class MapperTest {

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
	
	@Test
	public void testGetReferentielFromEntity(){
		
	}
	
	@Test
	public void testGetReferentielFromDAO(){
		
	}
	

}
