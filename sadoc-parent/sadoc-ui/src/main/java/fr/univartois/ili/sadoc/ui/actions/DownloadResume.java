package fr.univartois.ili.sadoc.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;

public class DownloadResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD, BaseColor.DARK_GRAY);
	private static Font particularFont = new Font(Font.FontFamily.TIMES_ROMAN,
			12, Font.NORMAL, BaseColor.BLACK);
	private static Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD, BaseColor.DARK_GRAY);
	private static Font empty = new Font(Font.FontFamily.HELVETICA, 20,
			Font.BOLD, BaseColor.WHITE);

	private int cv;
	private Map<String, Object> session;

	public int getCv() {
		return cv;
	}

	public void setCv(String cvs) {
		cv = Integer.parseInt(cvs);
	}

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	private void createPdf() {
		Document document = new Document();
		FileOutputStream fo;

		IMetierUIServices metierUIServices = null ;
		Resume resume =  metierUIServices.findResumeById(cv);
		try {
			session = ActionContext.getContext().getSession();
			fo = new FileOutputStream("/tmp/" + session.get("id") + "_" + cv
					+ "cv.pdf");

			Paragraph title = new Paragraph("Curriculum vitae", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);

			Paragraph prefaceNom = new Paragraph((String) session.get("name"),
					particularFont);
			Paragraph prefacePrenom = new Paragraph(
					(String) session.get("firstName"), particularFont);
			Paragraph prefaceAdresse = new Paragraph(
					(String) session.get("adress"), particularFont);
			Paragraph prefaceVille = new Paragraph(
					(String) session.get("zipCode") + ", "
							+ (String) session.get("town"), particularFont);
			Paragraph prefaceMail = new Paragraph((String) session.get("mail"),
					particularFont);
			Paragraph prefaceTel = new Paragraph((String) session.get("phone"),
					particularFont);
			prefaceNom.setAlignment(Element.ALIGN_LEFT);
			prefacePrenom.setAlignment(Element.ALIGN_LEFT);
			prefaceAdresse.setAlignment(Element.ALIGN_LEFT);
			prefaceVille.setAlignment(Element.ALIGN_LEFT);
			prefaceMail.setAlignment(Element.ALIGN_LEFT);
			prefaceTel.setAlignment(Element.ALIGN_LEFT);

			PdfWriter.getInstance(document, fo);
			document.open();
			document.add(title);
			document.add(prefaceNom);
			document.add(prefacePrenom);
			document.add(prefaceAdresse);
			document.add(prefaceVille);
			document.add(prefaceMail);
			document.add(prefaceTel);

			document.add(new Paragraph(" ",empty));
			document.add(new Paragraph(" ",empty));
			Paragraph prefaceTitleComp = new Paragraph("Compétences",
					sectionFont);
			prefaceTitleComp.setAlignment(Element.ALIGN_CENTER);
			document.add(prefaceTitleComp);
			document.add(new Paragraph(" ",empty));
			List<Competence> listComp = new ArrayList<Competence>(
					resume.getCompetences());

			Paragraph prefaceCompetence = new Paragraph(null, particularFont);
			prefaceCompetence.setAlignment(Element.ALIGN_CENTER);
			for (Competence comp : listComp) {
				prefaceCompetence.add(comp.getName());
				document.add(prefaceCompetence);
				prefaceCompetence.clear();
			}

			document.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (DocumentException e) {
			e.printStackTrace();

		}

	}

	public String execute() {

		session = ActionContext.getContext().getSession();
		if (session.get("mail") == null) {
			return "astalavista";
		}
		
		try {
			createPdf();

			fileInputStream = new FileInputStream(new File("/tmp/"
					+ session.get("id") + "_" + cv + "cv.pdf"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;

	}

	public void setSession(Map<String, Object> session) {
		session = this.getSession();
	}

	public Map<String, Object> getSession() {
		return session;
	}
}
