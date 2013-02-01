package fr.univartois.ili.sadoc.ws.pdf;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import fr.univartois.ili.sadoc.ws.utils.UtilsImgQrCod;

/**
 * @author jimmy
 * 
 */
public class PdfGen {

	private static final String genPdfProperties = "genPDF";
	private static final String QRC = "qrc";
	private static final String PREFIXE_URL = "prefixURL";

	private static final String URL_LOGO = ResourceBundle.getBundle(
			genPdfProperties).getString("URL_LOGO");
	private static final String URL_UNIV = ResourceBundle.getBundle(
			genPdfProperties).getString("URL_UNIV");

	private String pdfTestDeSortie = ResourceBundle.getBundle(genPdfProperties)
			.getString("pdfSortie");

	private static final String watermarkPath = "watermarkPath";
	private static final String logoPath = "logoPath";
	private static final String logoUnivPath = "logoUnivPath";

	private static final int QRCODE_POSITION_Y = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"QRCODE_POSITION_Y"));
	private static final int QRCODE_POSITION_X = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"QRCODE_POSITION_X"));

	private static final int LOGO_POSITION_Y = Integer.parseInt(ResourceBundle
			.getBundle(genPdfProperties).getString("LOGO_POSITION_Y"));
	private static final int LOGO_POSITION_X = Integer.parseInt(ResourceBundle
			.getBundle(genPdfProperties).getString("LOGO_POSITION_X"));

	private static final int LOGO_UNIV_POSITION_Y = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"LOGO_UNIV_POSITION_Y"));
	private static final int LOGO_UNIV_POSITION_X = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"LOGO_UNIV_POSITION_X"));

	private static final int WATERMARK_POSITION_X = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"WATERMARK_POSITION_X"));
	private static final int WATERMARK_POSITION_Y = Integer
			.parseInt(ResourceBundle.getBundle(genPdfProperties).getString(
					"WATERMARK_POSITION_Y"));

	private static final double PDF_SCALE_HEIGHT = Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"PDF_SCALE_HEIGHT"));
	private static final double PDF_SCALE_WIDTH = Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"PDF_SCALE_WIDTH"));

	private static final float PDF_SPACE_LEFT = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"PDF_SPACE_LEFT"));
	private static final float PDF_SPACE_BOTTOM = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"PDF_SPACE_BOTTOM"));

	private static final float TEXT_SADOC_LEFT_POSITION_X = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_LEFT_POSITION_X"));
	private static final float TEXT_SADOC_LEFT_POSITION_Y = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_LEFT_POSITION_Y"));
	private static final float TEXT_SADOC_LEFT_ROTATE = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_LEFT_ROTATE"));

	private static final float TEXT_SADOC_BOTTOM_POSITION_X = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_BOTTOM_POSITION_X"));
	private static final float TEXT_SADOC_BOTTOM_POSITION_Y = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_BOTTOM_POSITION_Y"));
	private static final float TEXT_SADOC_BOTTOM_ROTATE = (float) Double
			.parseDouble(ResourceBundle.getBundle(genPdfProperties).getString(
					"TEXT_SADOC_BOTTOM_ROTATE"));

	private static final int r = Integer.parseInt(ResourceBundle.getBundle(
			genPdfProperties).getString("red"));
	private static final int g = Integer.parseInt(ResourceBundle.getBundle(
			genPdfProperties).getString("green"));
	private static final int b = Integer.parseInt(ResourceBundle.getBundle(
			genPdfProperties).getString("blue"));

	// marges du pdf
	private static final int MARGIN = Integer.parseInt(ResourceBundle
			.getBundle(genPdfProperties).getString("MARGIN"));
	// taille en pixel du logo
	private static final int LOGO_SCALE = Integer.parseInt(ResourceBundle
			.getBundle(genPdfProperties).getString("LOGO_SCALE"));

	private com.itextpdf.text.Image watermark;
	private com.itextpdf.text.Image qrCode;
	private com.itextpdf.text.Image logo;
	private com.itextpdf.text.Image logoUniv;

	private PdfReader reader;
	private Image imgQrCode;
	private String urlQrCode;

	/**
	 * 
	 */
	public PdfGen() {
	}

	/**
	 * 
	 * @param reader
	 * @param imgQrCode
	 * @param id
	 */
	public PdfGen(PdfReader reader, Image imgQrCode, String id) {
		this.reader = reader;
		this.imgQrCode = imgQrCode;
		this.urlQrCode = ResourceBundle.getBundle(QRC).getString(PREFIXE_URL)
				+ id;
	}

	public byte[] generatePdf() {
		byte[] myDoc = null;
		try {
			logoConstruction();

			// creation du document
			Document doc = new Document(PageSize.A4);
			// Creation d'un buffer pour le pdf de sortie
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			// creation du pdf de sortie
			PdfWriter writer = PdfWriter.getInstance(doc, buffer);

			doc.open();
			// recuperation du contenu de premier plan
			PdfContentByte cb = writer.getDirectContent();
			// recuperation du contenu de second plan
			PdfContentByte under = writer.getDirectContentUnder();
			// construction des polices d'ecriture dans le pdf
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_OBLIQUE,
					BaseFont.WINANSI, BaseFont.EMBEDDED);
			// couleur des textes pour prouver l'authenticite
			Color textFiligranneColor = new Color(r, g, b);
			BaseColor paramTextFiliColor = new BaseColor(textFiligranneColor);
			// police des textes pour prouver l'authenticite
			Font fontTextFiliColor = new Font(bf);
			fontTextFiliColor.setColor(paramTextFiliColor);

			// construction du pdf
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				// nouvelle page
				doc.newPage();

				// marge sur le document
				doc.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
				doc.addCreator("SADoc");
				doc.addAuthor("SADoc");

				// generation de la page ( style de la premiere page pour toutes
				// les pages )
				generateFirstPage(doc, cb, under, writer, bf,
						paramTextFiliColor, i);

				// fin du texte
				cb.endText();
			}
			// fermeture du document
			doc.close();

			byte[] bytes = buffer.toByteArray();
			myDoc = bytes;

			// Pour tester en local en generant le pdf
			//FileOutputStream fout = new FileOutputStream(pdfTestDeSortie);
			//fout.write(bytes);
			//fout.close();

		} catch (Exception de) {
			de.printStackTrace();
		}

		return myDoc;
	}

	/**
	 * @throws BadElementException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void logoConstruction() throws BadElementException, IOException,
			URISyntaxException {

		ClassLoader loader = PdfGen.class.getClassLoader();

		URL watermUrl = loader.getResource(ResourceBundle.getBundle(
				genPdfProperties).getString(watermarkPath));
		URL logoUrl = loader.getResource(ResourceBundle.getBundle(
				genPdfProperties).getString(logoPath));
		URL univUrl = loader.getResource(ResourceBundle.getBundle(
				genPdfProperties).getString(logoUnivPath));

		Image imgWatermark = ImageIO.read(new File(watermUrl.getFile()));
		Image imglogo = ImageIO.read(new File(logoUrl.getFile()));
		Image imgUniv = ImageIO.read(new File(univUrl.getFile()));

		BufferedImage bufImgWatermark = UtilsImgQrCod
				.toBufferedImage(imgWatermark);
		BufferedImage bufImgLogo = UtilsImgQrCod.toBufferedImage(imglogo);
		BufferedImage bufImageQrCode = UtilsImgQrCod.toBufferedImage(imgQrCode);

		// redimension de l'image du logo
		bufImgLogo = (BufferedImage) UtilsImgQrCod.scale(bufImgLogo,
				LOGO_SCALE, LOGO_SCALE);
		// redimension de l'image du QR code
		// bufImageQrCode = (BufferedImage) Test.scale(bufImageQrCode, 75,
		// 75);

		// (superposition)
		// Ajout du logo sur le watermark (superposition)
		// BufferedImage watermarkImg = Utils.addImage(bufImgWatermark,
		// bufImgLogo, 0, 0);
		// Ajout du QRCODE sur le watermark
		// watermarkImg = Utils.addImage(watermarkImg, bufImageQrCode, 544,
		// 1);

		// construction de l'image avec la bibliotheque itext
		// permet de l'ajouter en fond du pdf
		watermark = com.itextpdf.text.Image.getInstance(bufImgWatermark, null);
		watermark.setAbsolutePosition(WATERMARK_POSITION_X,
				WATERMARK_POSITION_Y);

		// construction du qrCode
		qrCode = com.itextpdf.text.Image.getInstance(bufImageQrCode, null);
		qrCode.setAbsolutePosition(QRCODE_POSITION_X, QRCODE_POSITION_Y);
		// lien sur l'image
		Annotation annotationQrcode = new Annotation(0f, 0f, 0f, 0f, urlQrCode);
		qrCode.setAnnotation(annotationQrcode);
		// construction du logo de sadoc
		logo = com.itextpdf.text.Image.getInstance(bufImgLogo, null);
		logo.setAbsolutePosition(LOGO_POSITION_X, LOGO_POSITION_Y);
		// lien sur l'image
		Annotation annotationLogo = new Annotation(0f, 0f, 0f, 0f, URL_LOGO);
		logo.setAnnotation(annotationLogo);
		// construction du logo de l'universite d'artois
		logoUniv = com.itextpdf.text.Image.getInstance(imgUniv, null);
		logoUniv.setAbsolutePosition(LOGO_UNIV_POSITION_X, LOGO_UNIV_POSITION_Y);
		// lien sur l'image
		Annotation annotationLogoUniv = new Annotation(0f, 0f, 0f, 0f, URL_UNIV);
		logoUniv.setAnnotation(annotationLogoUniv);
	}

	/**
	 * @param doc
	 * @param cb
	 * @param under
	 * @param writer
	 * @param bf
	 * @param paramTextFiliColor
	 * @param numOfPage
	 * @throws DocumentException
	 */
	private void generateFirstPage(Document doc, PdfContentByte cb,
			PdfContentByte under, PdfWriter writer, BaseFont bf,
			BaseColor paramTextFiliColor, int numOfPage)
			throws DocumentException {

		// ajout du fond du pdf
		under.addImage(watermark);

		// ajout recuperation de la page du pdf de base
		PdfImportedPage page = writer.getImportedPage(reader, numOfPage);
		// ajout au pdf de la page du pdf de base
		// en la redimensionnant
		cb.addTemplate(page, (float) PDF_SCALE_HEIGHT /* echelle largeur */, 0,
				0, (float) PDF_SCALE_WIDTH/* echelle hauteur */,
				PDF_SPACE_LEFT/* marge gauche */, PDF_SPACE_BOTTOM/*
																 * marge bas
																 */);
		// debut de l'ecriture
		cb.beginText();
		// mise a jour de la taille de l'ecriture
		cb.setFontAndSize(bf, 10);
		// couleur de l'ecriture sur les pages (a gauche et en bas )
		cb.setColorFill(paramTextFiliColor);
		// ajout du text en fonction de l'angle ....
		cb.showTextAlignedKerned(1, "Document signé par l'Université d'Artois",
				TEXT_SADOC_LEFT_POSITION_X, TEXT_SADOC_LEFT_POSITION_Y,
				TEXT_SADOC_LEFT_ROTATE);
		cb.showTextAlignedKerned(1,
				"Vérifier en cliquant sur le code 2D ou en le flashant",
				TEXT_SADOC_BOTTOM_POSITION_X, TEXT_SADOC_BOTTOM_POSITION_Y,
				TEXT_SADOC_BOTTOM_ROTATE);
		// ajout des images
		cb.addImage(qrCode);
		cb.addImage(logo);
		cb.addImage(logoUniv);
	}

	/**
	 * @return the pdfSortie
	 */
	public String getPdfSortie() {
		return pdfTestDeSortie;
	}

	/**
	 * @param pdfSortie
	 *            the pdfSortie to set
	 */
	public void setPdfSortie(String pdfSortie) {
		this.pdfTestDeSortie = pdfSortie;
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
}