package fr.univartois.ili.sadoc.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;

import com.ibm.wsdl.util.IOUtils;
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
		if (sa != null /* && TestID.trueFalseID(sa) */) {
			// long realID = TestID.findRealID(sa);
			long realID = Long.valueOf(sa);
			DocumentDAO ddao = new DocumentDAO();
			AcquisitionDAO adao = new AcquisitionDAO();
			OwnerDAO odao = new OwnerDAO();
			CompetenceDAO cdao = new CompetenceDAO();
			Document doc = ddao.findById((int) realID);
			System.out.println("Recup WA");
			if (doc == null) {
				System.out.println("Pas sur WA");
				IClientWebService clientWebService = new ClientWebServiceImpl();

				fr.univartois.ili.sadoc.client.webservice.tools.Document docws = clientWebService
						.getDocument(realID);
				System.out.println("Requete sur WS");
				if (docws != null) {
					/*
					 * à modifier il faut convertir vers byte[], il faut
					 * l(adapter
					 */
					// DataHandler h=new DataHandler();

					/*
					 * DataHandler b=docws.getPk7().get(3);
					 * System.out.println("taille de la liste;"
					 * +docws.getPk7().size()); InputStream in; byte[] fakearray
					 * = null; try { in = b.getInputStream();
					 * 
					 * 
					 * 
					 * fakearray =org.apache.commons.io.IOUtils.toByteArray(in);
					 * } catch (IOException e) { // TODO Auto-generated catch
					 * block e.printStackTrace(); }
					 */
					Byte[] fakearraytmp = docws.getPk7();
					byte[] fakearray = new byte[fakearraytmp.length];
					for (int i = 0; i < fakearraytmp.length; i++) {
						fakearray[i] = fakearraytmp[i];
					}

					System.out.println("BYTE : " + fakearray[0] + fakearray[1]
							+ fakearray[2]);
					// here HABIB !!!
					Document doctoregister = new Document(docws.getName(),
							docws.getCheckSum(), "", fakearray, null);
					doctoregister.setId(docws.getId().intValue());
					ddao.create(doctoregister);

					document = doctoregister;
					System.out.println("mis du null dedans!!");
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
					System.out.println("Tout ajouté dans la BD!");
				} else {
					System.out.println("pas sur ws ou retour null");
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
}
