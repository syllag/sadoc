package fr.univartois.ili.sadoc.sadocweb.certificate;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MycerticateParserUses {

	/** Creates a new instance of Applic_Analyse */
	public MycerticateParserUses() {
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		MyCertificateCompetencesParser sp = new MyCertificateCompetencesParser(
				"src/test/resources/testCertificate.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();

		try {
			System.out.println("Creation du parser");
			SAXParser parser = spf.newSAXParser();
			parser.parse(new File(sp.getNomFichierXML()), sp);
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration du parser :"
					+ e.getMessage());
		} catch (SAXException e) {
			System.out.println("Erreur SAX :" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erreur IO :" + e.getMessage());
		}
	}

}