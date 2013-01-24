package fr.univartois.ili.sadoc.sadocweb.pdf.tests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.pdf.ManageQRCImpl;

/**
 * 
 * @author jimmy
 * 
 */
final class TestPdfGeneration {

	private TestPdfGeneration() {

	}

	private static final String genPdfProperties = "genPDF";

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) {
		try {
			PdfReader reader = new PdfReader("test.pdf");
			// On ne laisse pas de s.o.p lorsque l'on commit
			byte[] test = new ManageQRCImpl().generatePdfWithQrCode(reader,
					"453");
			FileOutputStream fout = new FileOutputStream(ResourceBundle
					.getBundle(genPdfProperties).getString("pdfSortie"));
			fout.write(test);
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
