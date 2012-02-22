package fr.univartois.ili.sadoc.client.webservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class ClientWebServiceImpl implements IClientWebService {


	public Document getDocument(long id) {
		// TODO Auto-generated method stub
		Calendar calendar = GregorianCalendar.getInstance();
		byte[] b = {1,0,0,1};
		calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		Date date = calendar.getTime();
		Document document = new Document("ROCOCO", "eghfgf","toto.com",b,date);
		return document;
	}

	public Map<Owner,List<Competence>> getCompetences(long idDoc) {
		// TODO Auto-generated method stub
		 
		Map<Owner,List<Competence>> mappy= new HashMap<Owner,List<Competence>>();
		//String firstName,String lastName,String mail, String password, String address,String zipCode,String town, String phone)
		Owner own = new Owner("Rococo","Nico","a@a.aa","bouloulou","123 rue moncul","69696","mabite","6969696969" );
		
		List<Competence> list= new ArrayList<Competence>();
		
		list.add(new Competence("branleur", "30 Kleenex par jours"));
		list.add(new Competence("geek", "30h de starWars en 2 jours"));
		
		
		mappy.put(own, list);
		
		return mappy;
	}
	

}
