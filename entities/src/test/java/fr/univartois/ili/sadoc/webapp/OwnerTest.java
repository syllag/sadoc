package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.Owner;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class OwnerTest {

	@Before
	public void initTests(){
		PersistenceProvider.setProvider("sadocjpatest");
	}
	
    @Test
    public void testPersist() throws ParseException {
    	final Owner user = new Owner();
    	user.setFirstName("Jean");
    	user.setLastName("Dupont");
    	user.setMail("jean.dupont@jaimal.com");
    	user.setPassword("hy846zef21fcv1frg846tr8rhj54ef1c0c10f846fraz351c");
    	user.setAddress("40 rue des pigeons");
    	user.setZipCode("62200");
    	user.setTown("Lens");
    	user.setPhone("0669696969");
    	
    	
    	OwnerDAO.create(user);
    	Owner userTest = OwnerDAO.findById(user.getId());
    	
        assertEquals(user.getId(), userTest.getId());
        assertEquals(user.getFirstName(), userTest.getFirstName());
        assertEquals(user.getLastName(), userTest.getLastName());
        assertEquals(user.getMail(), userTest.getMail());
        assertEquals(user.getPassword(), userTest.getPassword());
        assertEquals(user.getAddress(), userTest.getAddress());
        assertEquals(user.getZipCode(), userTest.getZipCode());
        assertEquals(user.getTown(), userTest.getTown());
        assertEquals(user.getPhone(), userTest.getPhone());
    }
    
    @After
	public void endTests(){
		PersistenceProvider.removeProvider();
	}
}
