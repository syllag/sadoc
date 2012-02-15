package fr.univartois.ili.sadoc.sadocweb.pdf.tests;

import java.awt.Image;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.pdf.PdfGen;
import fr.univartois.ili.sadoc.sadocweb.qrcode.ManageQRCodeIntegration;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;

class TestPdfGeneration implements ManageQRCodeIntegration {

	public TestPdfGeneration() {
		super();
	}

	public String generatePdfWithQrCode(PdfReader reader, String url) {
		Image imglQrCode = QRCodeWriterManager.getInstance().encode(url)
				.getImage();
		PdfGen pdfGen = new PdfGen(reader, imglQrCode, url);
		return "test : le fichier de sortie : " + pdfGen.generatePdf();
	}

	public static void main(String args[]) throws Exception {
		PdfReader reader = new PdfReader("test.pdf");

		System.out.println(new TestPdfGeneration().generatePdfWithQrCode(reader,
				"http://www.cril.univ-artois.fr/master/master-pro.html"));

	}

}
