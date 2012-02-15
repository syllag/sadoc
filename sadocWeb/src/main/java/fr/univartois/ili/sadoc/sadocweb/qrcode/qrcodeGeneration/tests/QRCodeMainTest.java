package fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.tests;

import java.awt.image.BufferedImage;

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

		BufferedImage test = QRCodeWriterManager
				.getInstance()
				.encode("http://www.cril.univ-artois.fr/master/master-pro.html")
				.getImage();
		BufferedImage test2 = QRCodeWriterManager2
				.getInstance()
				.encode("http://www.univ-artois.fr/0A1B2C3D4E5F6G7H8I9J10K11L12M_2345")
				.getImage();

		System.out.println("--->"
				+ QRCodeReaderManager.getInstance().decodeImage(test));
		System.out.println("--->"
				+ QRCodeReaderManager2.getInstance().decodeImage(test2));

	}

}
