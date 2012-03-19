package fr.univartois.ili.sadoc.sadocweb.certificate;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author francois
 * 
 */
public class MyXMLParserErrorHandler implements ErrorHandler {

	private boolean validate = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException arg0) throws SAXException {
		validate = false;
		arg0.printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException arg0) throws SAXException {
		validate = false;
		arg0.printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException arg0) throws SAXException {
		validate = false;
		arg0.printStackTrace();
	}

	/**
	 * @return
	 */
	public boolean isValidate() {
		return validate;
	}

}
