package fr.univartois.ili.sadoc.client.webservice;

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
		clientWebService.getOwner("dfgvdfgvdf@gmail.com");
		//clientWebService.createOwner("habib","belhadj","habib.bhs@gmail.com");

	}

}
