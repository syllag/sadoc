package fr.univartois.ili.sadoc.sadocweb.pdf;

import java.awt.Image;

import com.itextpdf.text.pdf.PdfReader;

import fr.univartois.ili.sadoc.sadocweb.qrcode.ManageQRC;
import fr.univartois.ili.sadoc.sadocweb.qrcode.qrcodeGeneration.QRCodeWriterManager;

public class ManageQRCImpl implements ManageQRC {

	public ManageQRCImpl() {
		super();
	}

	public String generatePdfWithQrCode(PdfReader reader, String url) {
		Image imglQrCode = QRCodeWriterManager.getInstance().encode(url)
				.getImage();
		PdfGen pdfGen = new PdfGen(reader, imglQrCode, url);
		return "test : le fichier de sortie : " + pdfGen.generatePdf();
	}
}
