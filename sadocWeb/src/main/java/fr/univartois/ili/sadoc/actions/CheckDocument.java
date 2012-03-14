package fr.univartois.ili.sadoc.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class CheckDocument extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sa = null;
	private Document document = null;
	private Owner owner = null;
	private List<Competence> listCompetences = new ArrayList<Competence>();

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
		if (sa!=null /*&& TestID.trueFalseID(sa)*/) {
			//long realID = TestID.findRealID(sa);
			long realID = Long.valueOf(sa);
			DocumentDAO ddao = new DocumentDAO();
			AcquisitionDAO adao = new AcquisitionDAO();
			OwnerDAO odao = new OwnerDAO();
			CompetenceDAO cdao = new CompetenceDAO();
			Document doc = ddao.findById((int) realID);

			if (doc == null) {
				IClientWebService clientWebService = new ClientWebServiceImpl();

				fr.univartois.ili.sadoc.client.webservice.tools.Document docws = clientWebService
						.getDocument(realID);
				if (docws != null) {
					/*
					 * à modifier il faut convertir  vers byte[], il faut l(adapter 
					 */
					Document doctoregister =null;//= //new Document(docws.getName(),docws.getCheckSum(), "", docws.getPk7(), null);
					//doctoregister.setId(docws.getId().intValue());
					ddao.create(doctoregister);
					document = doctoregister;

					clientWebService.getCompetences(docws.getId().longValue());
					Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> comp = clientWebService
							.getCompetences(docws.getId().longValue());
					fr.univartois.ili.sadoc.client.webservice.tools.Owner incowner = comp
							.keySet().iterator().next();

					Owner owntoregister = new Owner();
					owntoregister.setFirstName(incowner.getFirstName());
					owntoregister.setLastName(incowner.getLastName());
					owntoregister.setMail(incowner.getMail());
					owntoregister.setId(incowner.getId().intValue());
					odao.create(owntoregister);
					owner = owntoregister;

					for (fr.univartois.ili.sadoc.client.webservice.tools.Competence competence : comp
							.get(incowner)) {
						Competence c = new Competence(competence.getName(),
								competence.getDescription());
						c.setId(competence.getId().intValue());
						cdao.create(c);
						Acquisition a = new Acquisition();
						a.setCompetence(c);
						a.setDocument(doctoregister);
						a.setOwner(owntoregister);
						adao.create(a);
						listCompetences.add(c);
					}
				} else {
					return INPUT;
				}

			} else {
				document=doc;
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
}
