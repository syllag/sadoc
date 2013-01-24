/**
 * 
 */
package fr.univartois.ili.sadoc.sadocweb.init;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import fr.univartois.ili.sadoc.sadocweb.certificate.MyCertificateCompetencesParser;
import fr.univartois.ili.sadoc.sadocweb.repertory.MyScanRepertory;

/**
 * @author jimmy
 * 
 */
public class InitCompetences implements ServletContextListener {

	private static final String NAME_FILE_PROPERTIES = "initComp";
	private static final String PATH_COMP = "path";
	
	/** Methodes d'affichage */
	static protected void trace(String s) {
		Logger.getAnonymousLogger().warning(s);
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ResourceBundle bundle = ResourceBundle.getBundle(NAME_FILE_PROPERTIES);
		String repertory = bundle.getString(PATH_COMP);
				
		MyScanRepertory scanRepo = new MyScanRepertory();
		String[] tabFiles = scanRepo.getListFiles(repertory);

		MyCertificateCompetencesParser sp = null;
		for (String file : tabFiles) {
			trace("fichier a ouvrir : " + repertory + file);
			sp = new MyCertificateCompetencesParser(repertory + file);
			SAXParserFactory spf = SAXParserFactory.newInstance();

			try {
				trace("Creation du parser");
				SAXParser parser = spf.newSAXParser();
				parser.parse(new File(sp.getNomFichierXML()), sp);
			} catch (ParserConfigurationException e) {
				trace("Erreur de configuration du parser :"
						+ e.getMessage());
			} catch (SAXException e) {
				trace("Erreur SAX :" + e.getMessage());
			} catch (IOException e) {
				trace("Erreur IO :" + e.getMessage());
			}

		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
