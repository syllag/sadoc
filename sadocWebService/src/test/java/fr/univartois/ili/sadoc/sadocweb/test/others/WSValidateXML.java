package fr.univartois.ili.sadoc.sadocweb.test.others;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import fr.univartois.ili.sadoc.sadocweb.certificate.MyXMLParserErrorHandler;

/**
 * @author francois
 * 
 */
public class WSValidateXML {

	@Before
	public void initTest() {

	}

	@After
	public void endTest() {

	}

	/**+
	 * Verify validation xml
	 */
	@Test
	public void validateXML() {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			SchemaFactory s_factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			dbf.setSchema(s_factory.newSchema(new File(
					"src/main/resources/certificate.xsd")));
			DocumentBuilder db = dbf.newDocumentBuilder();
			MyXMLParserErrorHandler mxpeh = new MyXMLParserErrorHandler();
			db.setErrorHandler(mxpeh);
			db.parse(new File("src/test/resources/c2i2.xml"));
			assertTrue(mxpeh.isValidate());
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
