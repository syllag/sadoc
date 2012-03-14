package fr.univartois.ili.sadoc.client.webservice;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.Document;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.GetOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.Owner;

public class ClientWebServiceImpl implements IClientWebService {

	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(
			long id) {
		fr.univartois.ili.sadoc.client.webservice.tools.Document response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"fr/univartois/ili/sadoc/client/webservice/service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		GetDocumentRequest createDocumentRequest = new GetDocumentRequest();
		createDocumentRequest.setId(BigInteger.valueOf(id));

		response = (fr.univartois.ili.sadoc.client.webservice.tools.Document) webServiceTemplate
				.marshalSendAndReceive(createDocumentRequest);
		System.out.println("response:created client  :" + response.getId()
				+ " name: " + response.getName());
		return response;
	}

	public Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> getCompetences(
			long idDoc) {
		// TODO Auto-generated method stub

		// Map<Owner,List<Competence>> mappy= new
		// HashMap<Owner,List<Competence>>();
		// //String firstName,String lastName,String mail, String password,
		// String address,String zipCode,String town, String phone)
		// Owner own = new
		// Owner("Rococo","Nico","a@a.aa","bouloulou","123 rue moncul","69696","mabite","6969696969"
		// );
		//
		// List<Competence> list= new ArrayList<Competence>();
		//
		// list.add(new Competence("branleur", "30 Kleenex par jours"));
		// list.add(new Competence("geek", "30h de starWars en 2 jours"));
		//
		//
		// mappy.put(own, list);
		//
		return null;
	}

	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(
			String firstName, String lastName, String mail) {
		fr.univartois.ili.sadoc.client.webservice.tools.Owner response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"fr/univartois/ili/sadoc/client/webservice/service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {

			CreateOwnerRequest createOwnerRequest = new CreateOwnerRequest();
			createOwnerRequest.setFirstName(firstName);
			createOwnerRequest.setLastName(lastName);
			createOwnerRequest.setMail(mail);
			response = (fr.univartois.ili.sadoc.client.webservice.tools.Owner) webServiceTemplate
					.marshalSendAndReceive(createOwnerRequest);
			System.out.println("response:created client  :" + response.getId()
					+ " name: " + response.getLastName());

		} catch (Exception sfce) {

			System.out.println("We sent an invalid message" + sfce);

			sfce.printStackTrace();
		}

		return response;
	}

	public Owner getOwner(String mail) {
		// TODO Auto-generated method stub
		fr.univartois.ili.sadoc.client.webservice.tools.Owner response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"fr/univartois/ili/sadoc/client/webservice/service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {

			GetOwnerRequest getOwnerRequest = new GetOwnerRequest();

			getOwnerRequest.setMail(mail);
			response = (fr.univartois.ili.sadoc.client.webservice.tools.Owner) webServiceTemplate
					.marshalSendAndReceive(getOwnerRequest);
			System.out.println("response:created client  :" + response.getId()
					+ " name: " + response.getLastName());

		} catch (Exception sfce) {

			System.out.println("We sent an invalid message" + sfce);

			sfce.printStackTrace();
		}

		return response;
	}

	public List<Document> getAllDocument(long idOwner) {
		// TODO Auto-generated method stub
		return null;
	}

}
