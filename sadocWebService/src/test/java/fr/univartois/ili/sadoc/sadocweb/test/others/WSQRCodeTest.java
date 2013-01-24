package fr.univartois.ili.sadoc.sadocweb.test.others;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

import org.junit.Test;

import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeReaderManager;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;

public class WSQRCodeTest {

	/**
	 * Test encoding  and decoding data
	 */
	@Test
	public void testEncodeAndDecode() {
		String data = "id=AZERTY0123456789";
		
		// data encoding
		BufferedImage testbi = QRCodeWriterManager.getInstance().encode(data).getImage();
		
		// data decoding
		String data2 = QRCodeReaderManager.getInstance().decodeImage(testbi);
		
		ResourceBundle bundle = ResourceBundle.getBundle("qrc");
		String tmp = bundle.getString("prefixURL").concat(data);

		// compare String
		assertTrue(data2.trim().equals(tmp.trim()));
	}
	
}
