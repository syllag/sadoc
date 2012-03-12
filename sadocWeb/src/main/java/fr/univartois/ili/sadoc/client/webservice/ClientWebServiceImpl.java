package fr.univartois.ili.sadoc.client.webservice;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerReponse;
import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.Owner;


public class ClientWebServiceImpl implements IClientWebService {

	//@Resource(name="webServiceTemplate")
	//private WebServiceTemplate webServiceTemplate =new WebServiceTemplate();
	public fr.univartois.ili.sadoc.entities.Document getDocument(long id) {
		// TODO Auto-generated method stub
		Calendar calendar = GregorianCalendar.getInstance();
		byte[] b = {1,0,0,1};
		calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		Date date = calendar.getTime();
		//Document document = new Document("ROCOCO", "eghfgf","toto.com",b,date);
		return null;
	}

	public Map<Owner, List<fr.univartois.ili.sadoc.entities.Competence>> getCompetences(long idDoc) {
		// TODO Auto-generated method stub
		 
//		Map<Owner,List<Competence>> mappy= new HashMap<Owner,List<Competence>>();
//		//String firstName,String lastName,String mail, String password, String address,String zipCode,String town, String phone)
//		Owner own = new Owner("Rococo","Nico","a@a.aa","bouloulou","123 rue moncul","69696","mabite","6969696969" );
//		
//		List<Competence> list= new ArrayList<Competence>();
//		
//		list.add(new Competence("branleur", "30 Kleenex par jours"));
//		list.add(new Competence("geek", "30h de starWars en 2 jours"));
//		
//		
//		mappy.put(own, list);
//		
		return null;
	}

	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(
			) {
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"fr/univartois/ili/sadoc/client/webservice/service-client.xml");
		
		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {
			
			//webServiceTemplate.setDefaultUri("http://localhost:8080/sadocWeb/services/");
			CreateOwnerRequest createOwnerRequest =new CreateOwnerRequest();	
			createOwnerRequest.setFirstName("client1");
			createOwnerRequest.setLastName("habib");
			createOwnerRequest.setMail("habib.bhs@gmail.com");
			Owner response = (Owner) webServiceTemplate.marshalSendAndReceive(createOwnerRequest);
			System.out.println("rep :"+response.getId());
			
		}catch (Exception sfce) {
		       
		      // This indicates there's something wrong with our message
		      // For example a validation error
			System.out.println("We sent an invalid message"+ sfce);
		       
		      // Add error to model
		     // model.addAttribute("error", "Validation error! We cannot process your subscription");
			sfce.printStackTrace();
		     } 
		
		return null;
	}
	

}
