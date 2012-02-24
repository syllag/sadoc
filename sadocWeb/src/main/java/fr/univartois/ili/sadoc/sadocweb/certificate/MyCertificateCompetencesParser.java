package fr.univartois.ili.sadoc.sadocweb.certificate;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;

public class MyCertificateCompetencesParser extends DefaultHandler {

	protected String nomFichierXML;
	int cptTag = 0;

	Competence com = new Competence();
	
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

	/** Methodes d'affichage */
	static protected void trace(String s) {
		System.out.println(s);
	}

	static protected void trace(String Comment, String s) {
		System.out.println(Comment + " : " + s);
	}

	static protected void trace(String s, int i) {
		System.out.println(s + " : " + i);
	}

	/** Methodes du ContentHandler */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String chaine = new String(ch, start, length).trim();
		if (chaine.length() > 0){
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

			trace(" * Debut d'un element --> competence ");
			cptTag++;
			trace("++ compteur de tag :", cptTag);
			if (uri != null && uri.length() > 0)
				trace("   uri", uri);

			trace("  NomLocal", qName);

			if (uri != null && uri.length() > 0)
				trace("   NomComplet", qName);

			int nAttr = attr.getLength();
			trace("  Nombre d'attribut", nAttr);
		
			com = new Competence();
			
			if (nAttr == 0)
				return;
			for (int i = 0; i < nAttr; i++){
				trace("  attribut n° " + i + " = " + attr.getQName(i)
						+ " avec valeur : " + attr.getValue(i));
				com.setAcronym(attr.getValue(i));
				com.setName(attr.getValue(i));				
			}
			
		}
	}

	public void endElement(java.lang.String uri, java.lang.String localName,
			java.lang.String qName) throws SAXException {
		if ("competence".equals(qName)) {

			trace(" * Fin de l'element " + qName);
			cptTag++;
			trace("++ compteur de tag :", cptTag);
			
			trace(com.getAcronym() +" " + com.getName() +" " + com.getDescription() );
//			
//			CompetenceDAO cDao = new CompetenceDAO();
//			cDao.create(com);
		}
	}

}
