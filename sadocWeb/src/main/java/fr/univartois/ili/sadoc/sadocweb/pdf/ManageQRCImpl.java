package fr.univartois.ili.sadoc.sadocweb.pdf;

import java.awt.Image;

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
		Image imglQrCode = QRCodeWriterManager.getInstance().encode(id)
				.getImage();
		PdfGen pdfGen = new PdfGen(reader, imglQrCode, id);
		return pdfGen.generatePdf().getBytes();
	}
}
