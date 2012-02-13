package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.tests;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeReaderManager;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeReaderManager2;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager2;

/**
 * @author francois
 * 
 */
public class QRCodeMainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QRCodeWriterManager.getInstance().encode("img_qrc", "http://www.cril.univ-artois.fr/master/master-pro.html").getFileImage();
		QRCodeWriterManager2.getInstance().encode("img_qrc2", "http://www.univ-artois.fr/0A1B2C3D4E5F6G7H8I9J10K11L12M_2345").getFileImage();
		
		System.out.println("--->" + QRCodeReaderManager.getInstance().decode("img_qrc"));
		System.out.println("--->" + QRCodeReaderManager2.getInstance().decode("img_qrc2"));
		
	}

}
