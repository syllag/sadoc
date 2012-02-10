package fr.univartois.ili.sadoc.sadocweb.test;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.sadocweb.springbidon.ServiceObject;
/**
 * 
 * @author habib
 *
 */
public class AppTest extends TestCase {

	private static final Log LOG = LogFactory.getLog(AppTest.class);

	private static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext(
			"spring-config.xml");
	private ServiceObject serviceObject, serviceObject2;

	private long valeur = 12345, ruelav = 54321;

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		LOG.info("début test " + this.getName());
	}

	@Override
	protected void tearDown() throws Exception {
		LOG.info("fin test " + this.getName());
	}

	
	public void testIoC2() {
		serviceObject = (ServiceObject) APPLICATION_CONTEXT
				.getBean("serviceObject2");
		assertEquals(valeur, serviceObject.calculCA());
	}

	public void testIoC3() {
		serviceObject = (ServiceObject) APPLICATION_CONTEXT
				.getBean("serviceObject3");
		assertEquals(valeur, serviceObject.calculCA());
	}

	

}
