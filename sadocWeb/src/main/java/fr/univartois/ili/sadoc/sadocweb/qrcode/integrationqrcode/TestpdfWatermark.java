package fr.univartois.ili.sadoc.sadocweb.qrcode.integrationqrcode;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.itextpdf.text.pdf.PdfReader;

class TestpdfWatermark {
	
	//private String pdfSortie = "testExportCV.pdf";
	
	public static void main(String args[]) throws Exception {

		String qrCode = "img_qrc.png";
		Image imglQrCode = ImageIO.read(new File(qrCode));
		PdfReader reader = new PdfReader("test.pdf");
		PdfGen pdfGen = new PdfGen(reader,imglQrCode, "http://www.cril.univ-artois.fr/master/master-pro.html");
		System.out.println("test : le fichier de sortie : "+ pdfGen.generatePdf());
		
	}
}
