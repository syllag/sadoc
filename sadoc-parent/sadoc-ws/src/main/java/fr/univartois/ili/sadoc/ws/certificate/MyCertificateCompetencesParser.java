package fr.univartois.ili.sadoc.ws.certificate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.univartois.ili.sadoc.entitiesws.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
//TODO changement DAO
public class MyCertificateCompetencesParser extends DefaultHandler {

	protected String nomFichierXML;
	int cptTag = 0;

	private String nameCom = null;
	private Competence com = new Competence();
	private static final String FORMAT_DATE = "yyyy-MM-dd";

	/** Cree une nouvelle instance de Sax_Parser */
	public MyCertificateCompetencesParser() {
		setNomFichierXML(null);
	}

	public MyCertificateCompetencesParser(String nfx) {
		setNomFichierXML(nfx);
	}

	/** Methodes get et set */
	public String getNomFichierXML() {
		return nomFichierXML;
	}

	public void setNomFichierXML(String nfx) {
		nomFichierXML = nfx;
	}

	/** Methodes du ContentHandler */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String chaine = new String(ch, start, length).trim();
		if (chaine.length() > 0) {
			trace("@ Carateres", chaine);
			com.setDescription(chaine);
		}
	}

	public void startDocument() throws SAXException {
		trace("** Debut du document **");
	}

	public void endDocument() throws SAXException {
		trace("** Fin du document **");
	}

	public void startElement(java.lang.String uri, java.lang.String localName,
			java.lang.String qName, Attributes attr) throws SAXException {

		if ("competence".equals(qName)) {
			com = new Competence();

			trace(" * Debut d'un element --> competence ");

			cptTag++;
			trace("++ compteur de tag :", cptTag);
			if (uri != null && uri.length() > 0) {
				trace("   uri", uri);
			}

			trace("  NomLocal", qName);

			if (uri != null && uri.length() > 0) {
				trace("   NomComplet", qName);
			}

			int nAttr = attr.getLength();
			trace("  Nombre d'attribut", nAttr);

			if (nAttr == 0) {
				return;
			}
			for (int i = 0; i < nAttr; i++) {
				trace("  attribut n° " + i + " = " + attr.getQName(i)
						+ " avec valeur : " + attr.getValue(i));
				String accronym = attr.getValue(i);
				String strAccr[] = accronym.split(":");

				com.setAcronym(accronym);
				trace("Date de la competence " + strAccr[1] + "et son nom"
						+ nameCom);

				try {
					SimpleDateFormat spDate = new SimpleDateFormat(FORMAT_DATE);
					com.setCreationDate(spDate.parse(strAccr[1]));
				} catch (ParseException e) {
					trace("Erreur Enregistrement creation date competence : "
							+ e.toString());
				}

				com.setName(nameCom);
			}
		}
		if ("domain".equals(qName)) {
			int nAttr = attr.getLength();
			trace("  Nombre d'attribut", nAttr);

			if (nAttr == 0) {
				return;
			}
			nameCom = attr.getValue(1);
		}
	}

	public void endElement(java.lang.String uri, java.lang.String localName,
			java.lang.String qName) throws SAXException {
		if ("competence".equals(qName)) {

			trace(" * Fin de l'element " + qName);
			trace(" * Fin de l'element : resumer de la competence : "
					+ com.toString());
			cptTag++;
			trace("++ compteur de tag :", cptTag);

			trace(com.getAcronym() + " " + com.getName() + " "
					+ com.getDescription());

			CompetenceDAO cDao = new CompetenceDAO();

			cDao.getEntityManager().getTransaction().begin();
			cDao.create(com);
			cDao.getEntityManager().getTransaction().commit();
		}
		if ("domain".equals(qName)) {
			nameCom = null;
		}
	}

	/** Methodes d'affichage */
	static protected void trace(String s) {
		Logger.getAnonymousLogger().warning(s);
	}

	static protected void trace(String Comment, String s) {
		Logger.getAnonymousLogger().info(Comment + " : " + s);
	}

	static protected void trace(String s, int i) {
		Logger.getAnonymousLogger().warning(s + " : " + i);
	}

}
