package fr.univartois.ili.sadoc.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.client.webservice.IClientWebService;
import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.utils.TestID;

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

		if (sa != null && TestID.trueFalseID(sa)) {

			DocumentDAO ddao = new DocumentDAO();
			AcquisitionDAO adao = new AcquisitionDAO();
			OwnerDAO odao = new OwnerDAO();
			CompetenceDAO cdao = new CompetenceDAO();
			Document doc = ddao.findById(sa);

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
					ddao.create(doctoregister);

					document = doctoregister;
					clientWebService.getCompetences(docws.getId().longValue());
					Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> comp = clientWebService
							.getCompetences(docws.getId().longValue());
					fr.univartois.ili.sadoc.client.webservice.tools.Owner incowner = comp
							.keySet().iterator().next();

					owner = odao.findById(incowner.getId().intValue());
					if (owner == null) {
						Owner owntoregister = new Owner();
						owntoregister.setFirstName(incowner.getFirstName());
						owntoregister.setLastName(incowner.getLastName());
						owntoregister.setMail(incowner.getMail());
						owntoregister.setId(incowner.getId().intValue());
						odao.create(owntoregister);
						owner = owntoregister;
					}

					for (fr.univartois.ili.sadoc.client.webservice.tools.Competence competence : comp
							.get(incowner)) {

						Competence c = cdao.findById(competence.getId()
								.intValue());
						if (c == null) {
							c = new Competence(competence.getName(),
									competence.getDescription());
							c.setId(competence.getId().intValue());
							cdao.create(c);
						}
						Acquisition a = new Acquisition();
						a.setCompetence(c);
						a.setDocument(doctoregister);
						a.setOwner(owner);
						adao.create(a);
						listCompetences.add(c);
					}
				} else {
					return INPUT;
				}

			} else {
				document = doc;
				List<Acquisition> acq = adao.findByDocument(doc);
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
