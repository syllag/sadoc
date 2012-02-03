package fr.univartois.ili.sadoc.webapp;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import fr.univartois.ili.sadoc.dao.UserDAO;
import fr.univartois.ili.sadoc.entities.User;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class UserTest {

    @Test
    public void testPersist() throws ParseException {
    	final User user = new User();
    	user.setFirstName("Jean");
    	user.setLastName("Dupont");
    	user.setMail("jean.dupont@jaimal.com");
    	user.setPassword("hy846zef21fcv1frg846tr8rhj54ef1c0c10f846fraz351c");
    	user.setAddress("40 rue des pigeons");
    	user.setZipCode("62200");
    	user.setTown("Lens");
    	user.setPhone("0669696969");
    	
    	
    	UserDAO.create(user);
    	User userTest = UserDAO.findById(user.getId());
    	
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
}
