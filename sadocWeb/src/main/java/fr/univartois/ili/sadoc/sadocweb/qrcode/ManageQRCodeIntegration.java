package fr.univartois.ili.sadoc.sadocweb.qrcode;

import com.itextpdf.text.pdf.PdfReader;

public interface ManageQRCodeIntegration {

	public String generatePdfWithQrCode(PdfReader reader, String url);
	
}
