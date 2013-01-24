package fr.univartois.ili.sadoc.sadocweb.certificate;

import java.util.logging.Logger;

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
		trace("ERROR : Parsing XML File Competences ! ");
		validate = false;
		arg0.printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException arg0) throws SAXException {
		trace("FATAL ERROR : Parsing XML File Competences !");
		validate = false;
		arg0.printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException arg0) throws SAXException {
		trace("WARNING : Parsing XML File Competences !");
		validate = false;
		arg0.printStackTrace();
	}

	/**
	 * @return
	 */
	public boolean isValidate() {
		return validate;
	}

	private void trace(String s){
		Logger.getAnonymousLogger().severe(s);
	}
}
