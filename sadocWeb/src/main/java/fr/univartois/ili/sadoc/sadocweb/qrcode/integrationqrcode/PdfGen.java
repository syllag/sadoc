package fr.univartois.ili.sadoc.sadocweb.qrcode.integrationqrcode;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGen {

	private String watermark = "watermark.png";
	private String logo = "logoSaDoc.png";
	private String pdfSortie = "testExportCV.pdf";
	private PdfReader reader;
	private Image imgQrCode;
	private String urlQrCode;
	
	private static String URL_LOGO = "http://google.fr";
	private static int LARGEUR_QRCODE_PAGE = 544;
	private static int HAUTEUR_QRCODE_PAGE = 792;
	private static int HAUTEUR_LOGO_PAGE = 765;

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

			BufferedImage bufImgWatermark = UtilsImgQrCod.toBufferedImage(imgWatermark);
			BufferedImage bufImgLogo = UtilsImgQrCod.toBufferedImage(imglogo);
			BufferedImage bufImageQrCode = UtilsImgQrCod.toBufferedImage(imgQrCode);
			
			// redimension de l'image du logo
			bufImgLogo = (BufferedImage) UtilsImgQrCod.scale(bufImgLogo, 75, 75);
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
			watermark.setAbsolutePosition(0, 0);

			// construction du qrCode
			com.itextpdf.text.Image qrCode = com.itextpdf.text.Image
					.getInstance(bufImageQrCode, null);
			qrCode.setAbsolutePosition(LARGEUR_QRCODE_PAGE+1, 0);
			Annotation annotationQrcode = new Annotation(0f, 0f, 0f, 0f, urlQrCode);
			qrCode.setAnnotation(annotationQrcode);

			// construction du logo
			com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(
					bufImgLogo, null);
			logo.setAbsolutePosition(0, HAUTEUR_LOGO_PAGE);
			Annotation annotationLogo = new Annotation(0f, 0f, 0f, 0f, URL_LOGO);
			logo.setAnnotation(annotationLogo);

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
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
					BaseFont.WINANSI, BaseFont.EMBEDDED);

			Font blueFont = new Font();
			blueFont.setColor(0, 0, 0xFF);

			// construction du pdf
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				// nouvelle page
				doc.newPage();
				doc.setMargins(5, 5, 5, 5);
				doc.addCreator("SADoc");
				doc.addAuthor("SADoc");

				// ajout du fond du pdf
				under.addImage(watermark);
				under.addImage(qrCode);
				under.addImage(logo);
				
				// ajout recuperation de la page du pdf de base
				PdfImportedPage page = writer.getImportedPage(reader, i);
				// ajout au pdf de la page du pdf de base
				// en la redimensionnant
				cb.addTemplate(page, (float) 0.99, 0, 0, (float) 1, 30, 0);
				// debut de l'ecriture
				cb.beginText();
				// mise a jour de la taille de l'ecriture
				cb.setFontAndSize(bf, 11);
				// ajout du text en fonction de l'angle ....
				cb.showTextAlignedKerned(1,
						"Document Signé et vérifié par SADoc.", 30, 350, 90);

				cb.showTextAlignedKerned(1,
						"Ce document peut être vérifié en cliquant ici. ", 300, 25,
						0);
//				Chunk lienDocSigne = new Chunk("ici.", blueFont).setAnchor(urlQrCode);
//				Phrase p2 = new Phrase("Ce document peut être vérifié en cliquant ici. ");
//				Annotation ano = new Annotation("test","test");
//				doc.add(p2);
//				doc.add(ano);
				
				// fin du texte
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