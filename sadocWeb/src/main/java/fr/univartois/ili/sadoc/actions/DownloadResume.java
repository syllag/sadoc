package fr.univartois.ili.sadoc.actions;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class DownloadResume  extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cv;
	private Map<String, Object> session;
	
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
		session = ActionContext.getContext().getSession();
		if (session.get("mail")==null) {
			return "astalavista";
		}
		
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
	
	public void setSession(Map<String, Object> session){
		  session = this.getSession();
	}
	public Map<String, Object> getSession(){
		return session;
	}
}
