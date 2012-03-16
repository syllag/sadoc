package fr.univartois.ili.sadoc.sadocweb.pdf.tests;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.pdf.ManageQRCImpl;


/**
 * 
 * @author jimmy
 *
 */
final class TestPdfGeneration {

	private TestPdfGeneration(){
		
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]){
		try {
			PdfReader reader = new PdfReader("test.pdf");
			//On ne laisse pas de s.o.p lorsque l'on commit
			new ManageQRCImpl().generatePdfWithQrCode(reader,"http://www.cril.univ-artois.fr/master/master-pro.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
