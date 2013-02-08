package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
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
// TODO : Changer les appels
		if (sa != null && TestID.trueFalseID(sa)) {

			//## TODO injection spring pour interface
			IMetierUIServices metierUIServices = null;
						
//##			DocumentDAO ddao = new DocumentDAO();
//##			AcquisitionDAO adao = new AcquisitionDAO();
//##			OwnerDAO odao = new OwnerDAO();
//##			CompetenceDAO cdao = new CompetenceDAO();
			
			//##Document doc = ddao.findById(sa);
			Document doc = metierUIServices.findDocumentById(sa);
			
			if (doc == null) {

				IClientWebService clientWebService = new ClientWebServiceImpl();

				fr.univartois.ili.sadoc.client.webservice.tools.Document docws = clientWebService
						.getDocument(TestID.findRealID(sa));

				if (docws != null) {

					Byte[] fakearraytmp = docws.getPk7();
					byte[] fakearray = new byte[fakearraytmp.length];
					for (int i = 0; i < fakearraytmp.length; i++) {
						fakearray[i] = fakearraytmp[i];
					}

					Document doctoregister = new Document(docws.getName(),
							docws.getCheckSum(), "", fakearray, null);
					doctoregister.setId(TestID.createFalseID((docws.getId().longValue())));
					
					//##ddao.create(doctoregister);
					metierUIServices.createDocument(doctoregister);
					

					document = doctoregister;
					clientWebService.getCompetences(docws.getId().longValue());
					Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> comp = clientWebService
							.getCompetences(docws.getId().longValue());
					fr.univartois.ili.sadoc.client.webservice.tools.Owner incowner = comp
							.keySet().iterator().next();

					//##owner = odao.findById(incowner.getId().intValue());
					metierUIServices.findOwnerById(incowner.getId().intValue());
					
					if (owner == null) {
						Owner owntoregister = new Owner();
						owntoregister.setFirstName(incowner.getFirstName());
						owntoregister.setLastName(incowner.getLastName());
						owntoregister.setMail(incowner.getMail());
						owntoregister.setId(incowner.getId().intValue());
						
						//##odao.create(owntoregister);
						metierUIServices.createOwner(owntoregister);
						
						owner = owntoregister;
					}

					for (fr.univartois.ili.sadoc.client.webservice.tools.Competence competence : comp
							.get(incowner)) {

						//##Competence c = cdao.findById(competence.getId()
							//	.intValue());
						Competence c = metierUIServices.findCompetenceById(competence.getId().intValue());
						if (c == null) {
							c = new Competence(competence.getName(),
									competence.getDescription());
							c.setId(competence.getId().intValue());
							
							//##cdao.create(c);
							metierUIServices.createCompetence(c);
						}
						Acquisition a = new Acquisition();
						a.setCompetence(c);
						a.setDocument(doctoregister);
						a.setOwner(owner);
						
						//## adao.create(a);
						metierUIServices.createAcquisition(a);
						listCompetences.add(c);
					}
				} else {
					return INPUT;
				}

			} else {
				document = doc;
				//##List<Acquisition> acq = adao.findByDocument(doc);
				List<Acquisition> acq = metierUIServices.findAcquisitionByDocument(doc);
				owner = acq.get(0).getOwner();
				for (Acquisition a : acq) {
					listCompetences.add(a.getCompetence());
				}
			}

			return SUCCESS;
		}

		return INPUT;

	}

	public void setSession(Map<String, Object> session) {
		session = this.getSession();
	}

	public Map<String, Object> getSession() {
		return session;
	}
}