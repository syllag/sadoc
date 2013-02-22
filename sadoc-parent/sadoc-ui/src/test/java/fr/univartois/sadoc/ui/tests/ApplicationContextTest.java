/**
 * 
 */
package fr.univartois.sadoc.ui.tests;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import fr.univartois.ili.sadoc.ui.actions.CheckDocument;
import fr.univartois.ili.sadoc.ui.utils.MyFactory;
import static org.junit.Assert.*;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
public class ApplicationContextTest {

	@Test
	public void testApplicationContextLoadByClasspathProcess(){
		ApplicationContext app = MyFactory.getContext("classpath");
		assertNotNull(app);
	}
	
	@Test
	public void testApplicationContextLoadByFileSystemProcess(){
		ApplicationContext app = MyFactory.getContext("fileSystem");
		assertNotNull(app);
	}
	
	@Test
	public void testApplicationContextLoadByAutowired(){
		CheckDocument checkDocument = new CheckDocument();
		assertNotNull(checkDocument);
		assertNotNull(checkDocument.getMetierUIServices());
	}
}