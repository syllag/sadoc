package fr.univartois.ili.sadoc.client.webservice;

import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public interface IClientWebService {

	public Document getDocument(long idDoc);
	public Map<Owner,List<Competence>> getCompetences(long idDoc);
}
