package fr.univartois.ili.sadoc.client.webservice;

import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public interface IClientWebService {

	public Owner getOwner(int idDoc);
	public Document getDocument(int idDoc);
	
}
