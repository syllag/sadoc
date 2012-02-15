package fr.univartois.ili.sadoc.sadocweb.qrcode;

import com.itextpdf.text.pdf.PdfReader;

public interface ManageQRC {

	public String generatePdfWithQrCode(PdfReader reader, String url);
	
}
