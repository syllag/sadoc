package fr.univartois.ili.sadoc.sadocweb.test;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeReaderManager;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;

public class WSQRCodeTest {

	/**
	 * Test encoding  and decoding data
	 */
	
	public void testEncodeAndDecode() {
		String data = "id=AZERTY0123456789";
		
		// data encoding
		BufferedImage testbi = QRCodeWriterManager.getInstance().encode(data).getImage();
		
		// data decoding
		String data2 = QRCodeReaderManager.getInstance().decodeImage(testbi);
		
		// compare String
		assertTrue(data2.equals(data));
	}
	
}
