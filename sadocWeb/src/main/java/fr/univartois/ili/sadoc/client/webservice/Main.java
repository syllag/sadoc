package fr.univartois.ili.sadoc.client.webservice;

import fr.univartois.ili.sadoc.entities.Owner;

/**
 * il faut ignorer cette classe j'en ai besoin
 * @author habib
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		IClientWebService clientWebService =new ClientWebServiceImpl();
		clientWebService.getCompetences(1);
	    clientWebService.getOwner("a@gmail.com");
	    clientWebService.getDocument(1);
	    Owner o=new Owner();
	    o.setId(1);
	    o.setFirstName("vrfv");
	    o.setLastName("fdvfdv");
	    o.setMail("dfgvdfgvdf@gmail.com");
	    clientWebService.getAllDocument(o);
	    
		//clientWebService.createOwner("habib","belhadj","habib.bhs@gmail.com");

	}

}
