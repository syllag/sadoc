package fr.univartois.ili.sadoc.client.webservice;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerRequest;

public class ClientWebServiceImpl implements IClientWebService {

	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(
			long id) {

		Calendar calendar = GregorianCalendar.getInstance();
		byte[] b = { 1, 0, 0, 1 };
		calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		Date date = calendar.getTime();
		return null;
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
			System.out.println("response:created client  :" + response.getId()+" name: "+response.getLastName());

		} catch (Exception sfce) {

			System.out.println("We sent an invalid message" + sfce);

			sfce.printStackTrace();
		}

		return response;
	}

}
