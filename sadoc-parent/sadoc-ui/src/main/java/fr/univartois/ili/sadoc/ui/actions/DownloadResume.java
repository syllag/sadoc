package fr.univartois.ili.sadoc.ui.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.utils.Connection;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;
import fr.univartois.ili.sadoc.ui.utils.ResumeUtil;

public class DownloadResume extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private static final String SEPARATEUR = " - ";
	private static final String INDENT = " ";
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD, BaseColor.DARK_GRAY);
	private static Font particularFont = new Font(Font.FontFamily.TIMES_ROMAN,
			12, Font.NORMAL, BaseColor.BLACK);
	private static Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD, BaseColor.DARK_GRAY);
	private static Font empty = new Font(Font.FontFamily.HELVETICA, 20,
			Font.BOLD, BaseColor.WHITE);

	private InputStream fileInputStream;

	private int cv;
	private Map<String, Object> session;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	

	/**
	 * method createPdf
	 */

	private void createPdf(Owner owner) {
		Document document = new Document();
		FileOutputStream fo;
		
		Resume resume = metierUIServices.findResumeById(cv);
		if(resume.getOwner().getId() != owner.getId()) {
			throw new IllegalStateException();
		}
		
		try {
		
			fo = new FileOutputStream("/tmp/" + session.get("id") + "_" + cv
					+ "cv.pdf");

			Paragraph title = new Paragraph("Curriculum vitae", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph prefaceNom = new Paragraph((String) owner.getLastName(),
					particularFont);
			Paragraph prefacePrenom = new Paragraph(
					(String) owner.getFirstName(), particularFont);
			Paragraph prefaceAdresse = new Paragraph(
					(String) owner.getAddress(), particularFont);
			StringBuilder address = new StringBuilder();
			if(owner.getZipCode() != null){
				address.append(owner.getZipCode());
				address.append(' ');
			}
			if(owner.getTown() != null){
				address.append(owner.getTown());
			}
			Paragraph prefaceVille = new Paragraph(
					address.toString(), particularFont);
			Paragraph prefaceMail = new Paragraph((String) owner.getMail(),
					particularFont);
			Paragraph prefaceTel = new Paragraph((String) owner.getPhone(),
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

			document.add(new Paragraph(" ", empty));
			document.add(new Paragraph(" ", empty));
			Paragraph prefaceTitleComp = new Paragraph("Qualifications",
					sectionFont);
			prefaceTitleComp.setAlignment(Element.ALIGN_CENTER);
			document.add(prefaceTitleComp);
			document.add(new Paragraph(" ", empty));
			
			Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = ResumeUtil.generateMap(resume);
			
			Paragraph prefaceRef = new Paragraph(null, particularFont);
			int ind = 0;
			for (Map.Entry<Referentiel, Map<Domaine, Map <Competence, List<Item>>>> entRef : refWithDoms.entrySet()){
				document.add(new Paragraph(" ", empty));
				prefaceRef.add(entRef.getKey().getName());
				if(entRef.getValue().isEmpty()){
					prefaceRef.add(SEPARATEUR + entRef.getKey().getDescription());
					document.add(prefaceRef);
					prefaceRef.clear();
				}
				else{
					ind++;
					document.add(prefaceRef);
					prefaceRef.clear();
					Map <Domaine, Map <Competence, List<Item>>> domWithComps = refWithDoms.get(entRef.getKey());
					for (Map.Entry<Domaine, Map <Competence, List<Item>>> entDom : domWithComps.entrySet()){
						prefaceRef.add(indent(ind) + entDom.getKey().getCodeDomaine());
						if(entDom.getValue().isEmpty()){
							prefaceRef.add(SEPARATEUR + entDom.getKey().getDescription());
							document.add(prefaceRef);
							prefaceRef.clear();
							ind--;
						}
						else{
							ind++;
							document.add(prefaceRef);
							prefaceRef.clear();
							Map <Competence, List<Item>> compWithItems = domWithComps.get(entDom.getKey());
							for (Map.Entry<Competence, List<Item>> entComp : compWithItems.entrySet()){
								prefaceRef.add(indent(ind) + entComp.getKey().getCodeCompetence());
								if(entComp.getValue().isEmpty()){
									prefaceRef.add(SEPARATEUR + entComp.getKey().getDescription());
									document.add(prefaceRef);
									prefaceRef.clear();
									ind--;
								}
								else{
									ind++;
									document.add(prefaceRef);
									prefaceRef.clear();
									List<Item> items = compWithItems.get(entComp.getKey());
									for(Item it : items){
										prefaceRef.add(indent(ind) + it.getCodeItem());
										prefaceRef.add(SEPARATEUR + it.getDescription());
										document.add(prefaceRef);
										prefaceRef.clear();										
									}
									ind--;
								}
							}
							ind--;
						}
					}
					ind--;
				}
				
			}

			document.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * generate the indentation 
	 * @param n
	 * @return the string of the indentation
	 */
	private String indent(int n){
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < n; i++){
			buf.append(INDENT);
		}
		return buf.toString();
	}
	


	public String execute() {
		
		Owner owner = Connection.getUser(session);
		if (owner == null){
			return "index";
		}
		
		try {
			createPdf(owner);
			String pdf_path = "/tmp/"
					+ session.get("id") + "_" + cv + "cv.pdf";
			LOG.info("PDF generated at "+pdf_path);
			fileInputStream = new FileInputStream(new File(pdf_path));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		}
		 catch (IllegalStateException e) {
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;

	}

	/**
	 * getter and setter of Cv
	 */
	public int getCv() {
		return cv;
	}

	public void setCv(String cvs) {
		cv = Integer.parseInt(cvs);
	}
	
	/**
	 * getter and setter of Session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}
	
	/**
	 * @return the fileInputStream
	 */
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
}
