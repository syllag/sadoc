package fr.univartois.ili.sadoc.actions;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.utils.TestID;

public class CheckDocument extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sa = null;
	private Document document = null;
	private Owner owner = null;
	private List<Competence> listCompetences = null;

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
		if (TestID.trueFalseID(sa)) {
			long realID = TestID.findRealID(sa);
			ClientWebServiceImpl clientWS = new ClientWebServiceImpl();
			document = clientWS.getDocument(realID);

			Map<Owner, List<Competence>> competences = clientWS
					.getCompetences(realID);

			for (Owner user : competences.keySet()) {
				owner = user;
				listCompetences = competences.get(owner);
			}
			return SUCCESS;
		}

		return INPUT;
	}

}
