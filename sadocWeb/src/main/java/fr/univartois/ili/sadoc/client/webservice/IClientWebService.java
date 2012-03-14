package fr.univartois.ili.sadoc.client.webservice;

import java.util.List;
import java.util.Map;

public interface IClientWebService {

	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(long idDoc);
	public List<fr.univartois.ili.sadoc.client.webservice.tools.Document> getAllDocument(long idOwner);
	public Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> getCompetences(long idDoc);
	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(String firstName ,String lastName, String mail);
	public fr.univartois.ili.sadoc.client.webservice.tools.Owner getOwner(String mail);
}
