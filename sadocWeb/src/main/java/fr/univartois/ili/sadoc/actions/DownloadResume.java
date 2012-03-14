package fr.univartois.ili.sadoc.actions;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;


public class DownloadResume  extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cv;
	
	public int getCv(){
		return cv;
	}
	
	public void setCv(String cvs){
		cv=Integer.parseInt(cvs);
	}
	
	private InputStream fileInputStream;
	 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	public String execute() {
		
		Document document=new Document();
		FileOutputStream fo;
		try {
			fo = new FileOutputStream("/tmp/cvTEST.pdf");

		
		PdfWriter.getInstance(document,fo);
		
		Paragraph prefaceNom = new Paragraph("Coucou je test la création d'un pdf et le download ! ! !");
		
		document.open();
		document.add(prefaceNom);
		document.close();
		
	    fileInputStream = new FileInputStream(new File("/tmp/cvTEST.pdf"));
	    
	    System.out.println("id cv : "+cv);
	    
	    return SUCCESS;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		} catch (DocumentException e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
}
