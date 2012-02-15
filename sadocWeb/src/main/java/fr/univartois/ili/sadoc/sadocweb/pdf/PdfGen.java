package fr.univartois.ili.sadoc.sadocweb.pdf;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import fr.univartois.ili.sadoc.sadocweb.pdf.utils.UtilsImgQrCod;

public class PdfGen {

	private String flecheD = "flecheD.png";
	private String watermark = "watermark.png";
	private String logo = "logoSaDoc.png";
	private String logoUniv = "logoUnivArtois.png";
	private String pdfSortie = "testExportCV.pdf";
	private PdfReader reader;
	private Image imgQrCode;
	private String urlQrCode;

	private static final String URL_LOGO = "http://code.google.com/p/sadoc";
	private static final String URL_UNIV = "http://www.univ-artois.fr";
	
	private static final int QRCODE_POSITION_Y = 0;
	private static final int QRCODE_POSITION_X = 546;

	private static final int LOGO_POSITION_Y = 765;
	private static final int LOGO_POSITION_X = 0;
	
	private static final int LOGO_UNIV_POSITION_Y = 12;
	private static final int LOGO_UNIV_POSITION_X = 0;

	private static final int WATERMARK_POSITION_X = 0;
	private static final int WATERMARK_POSITION_Y = 0;
	
	private static final int FLECHED_POSITION_X = 490;
	private static final int FLECHED_POSITION_Y = 15;

	private static final double PDF_SCALE_HEIGHT = 0.9;
	private static final double PDF_SCALE_WIDTH = 0.9;

	private static final float PDF_SPACE_LEFT = 50;
	private static final float PDF_SPACE_BOTTOM = 50;

	private static final float TEXT_SADOC_LEFT_POSITION_X = 28;
	private static final float TEXT_SADOC_LEFT_POSITION_Y = 350;
	private static final float TEXT_SADOC_LEFT_ROTATE = 90;

	private static final float TEXT_SADOC_BOTTOM_POSITION_X = 300;
	private static final float TEXT_SADOC_BOTTOM_POSITION_Y = 21;
	private static final float TEXT_SADOC_BOTTOM_ROTATE = 0;

	// marges du pdf
	private static final int MARGIN = 5;
	// taille en pixel du logo
	private static final int LOGO_SCALE = 75;

	public PdfGen() {
	}

	public PdfGen(PdfReader reader, Image imgQrCode, String url) {
		this.reader = reader;
		this.imgQrCode = imgQrCode;
		this.urlQrCode = url;
	}

	public String generatePdf() {
		try {
			Image imgWatermark = ImageIO.read(new File(watermark));
			Image imglogo = ImageIO.read(new File(logo));
			Image imgFlecheD = ImageIO.read(new File(flecheD));
			Image imgUniv = ImageIO.read(new File(logoUniv));

			BufferedImage bufImgWatermark = UtilsImgQrCod
					.toBufferedImage(imgWatermark);
			BufferedImage bufImgLogo = UtilsImgQrCod.toBufferedImage(imglogo);
			BufferedImage bufImageQrCode = UtilsImgQrCod
					.toBufferedImage(imgQrCode);

			// redimension de l'image du logo
			bufImgLogo = (BufferedImage) UtilsImgQrCod.scale(bufImgLogo,
					LOGO_SCALE, LOGO_SCALE);
			// redimension de l'image du QR code
			// bufImageQrCode = (BufferedImage) Test.scale(bufImageQrCode, 75,
			// 75);

			// Ajout du logo sur le watermark
			// BufferedImage watermarkImg = Utils.addImage(bufImgWatermark,
			// bufImgLogo, 0, 0);

			// Ajout du QRCODE sur le watermark
			// watermarkImg = Utils.addImage(watermarkImg, bufImageQrCode, 544,
			// 1);

			// construction de l'image avec la bibliotheque itext
			// permet de l'ajouter en fond du pdf
			com.itextpdf.text.Image watermark = com.itextpdf.text.Image
					.getInstance(bufImgWatermark, null);
			watermark.setAbsolutePosition(WATERMARK_POSITION_X,
					WATERMARK_POSITION_Y);

			// construction du qrCode
			com.itextpdf.text.Image qrCode = com.itextpdf.text.Image
					.getInstance(bufImageQrCode, null);
			qrCode.setAbsolutePosition(QRCODE_POSITION_X, QRCODE_POSITION_Y);
			Annotation annotationQrcode = new Annotation(0f, 0f, 0f, 0f,
					urlQrCode);
			qrCode.setAnnotation(annotationQrcode);

			// construction du logo
			com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(
					bufImgLogo, null);
			logo.setAbsolutePosition(LOGO_POSITION_X, LOGO_POSITION_Y);
			Annotation annotationLogo = new Annotation(0f, 0f, 0f, 0f, URL_LOGO);
			logo.setAnnotation(annotationLogo);

			com.itextpdf.text.Image flecheD = com.itextpdf.text.Image
					.getInstance(imgFlecheD, null);
			flecheD.setAbsolutePosition(FLECHED_POSITION_X,
					FLECHED_POSITION_Y);
			
			com.itextpdf.text.Image logoUniv = com.itextpdf.text.Image
					.getInstance(imgUniv, null);
			logoUniv.setAbsolutePosition(LOGO_UNIV_POSITION_X,
					LOGO_UNIV_POSITION_Y);
			Annotation annotationLogoUniv = new Annotation(0f, 0f, 0f, 0f, URL_UNIV);
			logoUniv.setAnnotation(annotationLogoUniv);
			
			// creation du document
			Document doc = new Document(PageSize.A4);
			// creation du pdf de sortie
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(
					pdfSortie));
			doc.open();
			// recuperation du contenu de premier plan
			PdfContentByte cb = writer.getDirectContent();
			// recuperation du contenu de second plan
			PdfContentByte under = writer.getDirectContentUnder();
			// construction des polices d'ecriture dans le pdf
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE,
					BaseFont.WINANSI, BaseFont.EMBEDDED);

			Font blueFont = new Font();
			blueFont.setColor(0, 0, 0xFF);

			// construction du pdf
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				// nouvelle page
				doc.newPage();
				doc.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
				doc.addCreator("SADoc");
				doc.addAuthor("SADoc");

				// ajout du fond du pdf
				under.addImage(watermark);
				under.addImage(flecheD);
				under.addImage(logoUniv);
				// under.addImage(logo);

				// ajout recuperation de la page du pdf de base
				PdfImportedPage page = writer.getImportedPage(reader, i);
				// ajout au pdf de la page du pdf de base
				// en la redimensionnant
				cb.addTemplate(page,
						(float) PDF_SCALE_HEIGHT /* echelle largeur */, 0, 0,
						(float) PDF_SCALE_WIDTH/* echelle hauteur */,
						PDF_SPACE_LEFT/* marge gauche */, PDF_SPACE_BOTTOM/*
																		 * marge
																		 * bas
																		 */);
				// debut de l'ecriture
				cb.beginText();
				// mise a jour de la taille de l'ecriture
				cb.setFontAndSize(bf, 11);
				// ajout du text en fonction de l'angle ....
				cb.showTextAlignedKerned(1,
						"Document signé par l'Université d'Artois.",
						TEXT_SADOC_LEFT_POSITION_X, TEXT_SADOC_LEFT_POSITION_Y,
						TEXT_SADOC_LEFT_ROTATE);

				cb.showTextAlignedKerned(1,
						"La version numérique de ce document peut être vérifié en cliquant ici",
						TEXT_SADOC_BOTTOM_POSITION_X,
						TEXT_SADOC_BOTTOM_POSITION_Y, TEXT_SADOC_BOTTOM_ROTATE);

				// Chunk lienDocSigne = new Chunk("ici.",
				// blueFont).setAnchor(urlQrCode);
				// Phrase p2 = new
				// Phrase("Ce document peut être vérifié en cliquant ici. ");
				// Annotation ano = new Annotation("test","test");
				// doc.add(p2);
				// doc.add(ano);

				// fin du texte
				cb.addImage(qrCode);
				cb.addImage(logo);
				cb.endText();
			}
			// fermeture du document
			doc.close();

		} catch (Exception de) {
			de.printStackTrace();
		}
		return pdfSortie;
	}

	/**
	 * @return the pdfSortie
	 */
	public String getPdfSortie() {
		return pdfSortie;
	}

	/**
	 * @param pdfSortie
	 *            the pdfSortie to set
	 */
	public void setPdfSortie(String pdfSortie) {
		this.pdfSortie = pdfSortie;
	}

	/**
	 * @return the reader
	 */
	public PdfReader getReader() {
		return reader;
	}

	/**
	 * @param reader
	 *            the reader to set
	 */
	public void setReader(PdfReader reader) {
		this.reader = reader;
	}

	/**
	 * @return the imgQrCode
	 */
	public Image getImgQrCode() {
		return imgQrCode;
	}

	/**
	 * @param imgQrCode
	 *            the imgQrCode to set
	 */
	public void setImgQrCode(Image imgQrCode) {
		this.imgQrCode = imgQrCode;
	}

	/**
	 * @return the watermark
	 */
	public String getWatermark() {
		return watermark;
	}

	/**
	 * @param watermark
	 *            the watermark to set
	 */
	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
}