package fr.univartois.ili.sadoc.ws.qrcode;

import com.itextpdf.text.pdf.PdfReader;

public interface IManageQRC {

	public byte[] generatePdfWithQrCode(PdfReader reader, String url);
	
}
