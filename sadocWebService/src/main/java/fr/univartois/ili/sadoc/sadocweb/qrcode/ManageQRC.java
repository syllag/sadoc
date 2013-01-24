package fr.univartois.ili.sadoc.sadocweb.qrcode;

import com.itextpdf.text.pdf.PdfReader;

public interface ManageQRC {

	public byte[] generatePdfWithQrCode(PdfReader reader, String url);
	
}
