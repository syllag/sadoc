package fr.univartois.ili.sadoc.sadocweb.pdf;

import java.awt.Image;
import java.util.logging.Logger;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.qrcode.ManageQRC;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;

/**
 * @author jimmy
 *
 */
public class ManageQRCImpl implements ManageQRC {

	/**
	 * 
	 */
	public ManageQRCImpl() {
		super();
	}

	
	/* (non-Javadoc)
	 * @see fr.univartois.ili.sadoc.sadocweb.qrcode.ManageQRC#generatePdfWithQrCode(com.itextpdf.text.pdf.PdfReader, java.lang.String)
	 */
	public byte[] generatePdfWithQrCode(PdfReader reader, String id) {
		Logger.getAnonymousLogger().warning("Creation du QRCODE !! ");
		Image imglQrCode = QRCodeWriterManager.getInstance().encode(id)
				.getImage();
		Logger.getAnonymousLogger().warning("QRCODE recupere !! ");
		Logger.getAnonymousLogger().warning("GENERATION Du PDF !! ");
		PdfGen pdfGen = new PdfGen(reader, imglQrCode, id);
		return pdfGen.generatePdf();

	}
}
