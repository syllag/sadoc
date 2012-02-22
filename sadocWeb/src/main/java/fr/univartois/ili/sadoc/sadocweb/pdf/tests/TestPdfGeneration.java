package fr.univartois.ili.sadoc.sadocweb.pdf.tests;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.pdf.ManageQRCImpl;

/**
 * 
 * @author jimmy
 *
 */
class TestPdfGeneration {

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		PdfReader reader = new PdfReader("test.pdf");

		System.out.println(new ManageQRCImpl().generatePdfWithQrCode(reader,
				"http://www.cril.univ-artois.fr/master/master-pro.html"));

	}

}
