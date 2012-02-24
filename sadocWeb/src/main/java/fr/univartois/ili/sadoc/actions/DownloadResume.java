package fr.univartois.ili.sadoc.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
 
	public String execute() throws Exception {
	    fileInputStream = new FileInputStream(new File("/home/releng/tempCV/cv.pdf"));
	    
	    System.out.println("id cv : "+cv);
	    
	    return SUCCESS;
	}
	
}
