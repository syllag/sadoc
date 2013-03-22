package fr.univartois.ili.sadoc.client.webservice;

import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;


/**
 * manage client web service
 * @author habib
 *
 */
//TODO : remonter l'appel web service côté métier
public interface IClientWebService {

	/**
	 * get document by id 
	 * @param idDoc
	 * @return
	 */
	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(long idDoc);
	/**
	 * get liste des document by owner
	 * @param owner
	 * @return
	 */
	public List<fr.univartois.ili.sadoc.client.webservice.tools.Document> getAllDocument(Owner owner);
	/**
	 * get owner and liste of competance by idDoc
	 * @param idDoc
	 * @return
	 */
	public Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Acquisition>> getAcquisitions(long idDoc);
	/**
	 * create owner
	 * @param firstName
	 * @param lastName
	 * @param mail
	 * @return
	 */
	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(String firstName ,String lastName, String mail);
	/**
	 * get owner by her mail
	 * @param mail
	 * @return
	 */
	public fr.univartois.ili.sadoc.client.webservice.tools.Owner getOwner(String mail);
}
