package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.client.webservice.IClientWebService;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;
import fr.univartois.ili.sadoc.ui.utils.TestID;

public class CheckDocument extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sa = null;
	private Document document = null;
	private Owner owner = null;
	private List<Competence> listCompetences = new ArrayList<Competence>();
	private Map<String, Object> session;
	
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;

	public String getSa() {
		return sa;
	}

	public void setSa(final String sa) {
		this.sa = sa;
	}

	public Document getDocument() {
		return document;
	}

	public List<Competence> getListCompetences() {
		return listCompetences;
	}

	public Owner getOwner() {
		return owner;
	}
	

	public String execute() {
		//if (sa != null && TestID.trueFalseID(sa)) {
		//	Document doc = metierUIServices.findDocumentById(sa);
		if (true) {
			Document doc = Document.getFakeDocument();
		
			if (doc == null) {

				IClientWebService clientWebService = new ClientWebServiceImpl();

				fr.univartois.ili.sadoc.client.webservice.tools.Document docws = clientWebService
						.getDocument(TestID.findRealID(sa));

				if (docws != null) {

					// TODO anicet :) le pk7 n'existe plus
					//Byte[] fakearraytmp = docws.getPk7();
					Byte[] fakearraytmp = docws.getP7s();
					byte[] fakearrayP7S = new byte[fakearraytmp.length];
					for (int i = 0; i < fakearraytmp.length; i++) {
						fakearrayP7S[i] = fakearraytmp[i];
					}

					
					Document doctoregister = new Document();
					doctoregister.setName(docws.getName());
					doctoregister.setCheckSum(docws.getCheckSum());
					doctoregister.setP7s(fakearrayP7S);
					doctoregister.setUrl("");
					doctoregister.setCreationDate(new Date());
					doctoregister.setId(TestID.createFalseID((docws.getId())));
					
					metierUIServices.createDocument(doctoregister);
					document = doctoregister;
					
					clientWebService.getCompetences(docws.getId());
					Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> comp = clientWebService
							.getCompetences(docws.getId());
					fr.univartois.ili.sadoc.client.webservice.tools.Owner incowner = comp
							.keySet().iterator().next();

					metierUIServices.findOwnerById(incowner.getId());
					
					if (owner == null) {
						
						Owner owntoregister = new Owner();
//						owntoregister.setFirstName(incowner.getFirstName());
//						owntoregister.setLastName(incowner.getLastName());
						owntoregister.setMail(incowner.getMail_initial());

						owntoregister.setId(incowner.getId());
						
						metierUIServices.createOwner(owntoregister);
						owner = owntoregister;
					}

					//TODO les competences sont remplacées par les acquisitions
					for (fr.univartois.ili.sadoc.client.webservice.tools.Competence competence : comp
							.get(incowner)) {
						Competence c = metierUIServices.findCompetenceById(competence.getId());
						if (c == null) {
							c = new Competence();
							c.setName(competence.getName());
							c.setDescription(competence.getDescription());
							c.setId(competence.getId());
							
							metierUIServices.createCompetence(c);
						}
						Acquisition a = new Acquisition();
						a.setCompetence(c);
						a.setDocument(doctoregister);
						a.setOwner(owner);
						
						metierUIServices.createAcquisition(a);
						listCompetences.add(c);
					}
				} else {
					return INPUT;
				}

			} else {
				document = doc;

//				List<Acquisition> acq = metierUIServices.findAcquisitionByDocument(doc);
//				owner = acq.get(0).getOwner();
//				for (Acquisition a : acq) {
//					listCompetences.add(a.getCompetence());
//				}
			}

			return SUCCESS;
		}

		return INPUT;

	}

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
}
