package fr.univartois.ili.sadoc.ui.actions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Degree;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.ili.sadoc.ui.form.ManageConnectForm;

/**
 * @author Damien Wattiez <Damien Wattiez at gmail.com>
 * 
 */
public class ManageConnect extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * form contains connect
	 */
	private ManageConnectForm connect;
	private Map<String, Object> session;

	public String execute() {
		// Create session
		session = ActionContext.getContext().getSession();
		
		//## TODO injection
		IMetierUIServices metierUIServices = null ;
		
		if (session.get("mail") != null) {
			return SUCCESS;
		}

		if (connect == null) {
			session.put("incorrect", "");
			return INPUT;
		}
		
//##		OwnerDAO odao = new OwnerDAO();
		
		
		Owner owner = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] p = messageDigest.digest(connect.getPassword().getBytes());
			StringBuilder hashString = new StringBuilder();
			for (int i = 0; i < p.length; ++i) {
				String hex;
				hex = Integer.toHexString(p[i]);
				if (hex.length() == 1) {
					hashString.append('0');
					hashString.append(hex.charAt(hex.length() - 1));
				} else {
					hashString.append(hex.substring(hex.length() - 2));
				}
			}

//#			owner = odao.findOwner(connect.getEmail(), hashString.toString());
			owner = metierUIServices.findOwnerByEmailAndPassword(connect.getEmail(), hashString.toString());
			
		} catch (NoSuchAlgorithmException e) {
		}

		// if empty
		if (owner == null) {
			session.put("incorrect", "Password ou mail incorrect");
			return INPUT;
		}
		session.put("id", owner.getId());
		session.put("firstname", owner.getFirstName());
		session.put("name", owner.getLastName());
		session.put("adress", owner.getAddress());
		session.put("town", owner.getTown());
		session.put("zipCode", owner.getZipCode());
		session.put("phone", owner.getPhone());
		session.put("mail", owner.getMail());
		session.put("listResume", owner.getResumes());
		// session.put("listResume", getFakeResumes(owner));

		session.put("mapCompetence", getMapCompetence(owner));
		// session.put("mapCompetence", getFakeMapCompetence(owner));

		return SUCCESS;
	}

	private Map<Competence, List<Document>> getFakeMapCompetence(Owner owner) {
		Set<Competence> setCompetence = new HashSet<Competence>();
		Competence comp1 = new Competence("nomCompetence1", "descCompetence1");
		comp1.setId(0);
		comp1.setAcronym("CMP1");
		setCompetence.add(comp1);
		Competence comp2 = new Competence("nomCompetence2", "descCompetence2");
		comp2.setId(1);
		comp2.setAcronym("CMP2");
		setCompetence.add(comp2);

		Map<Competence, List<Document>> map = new HashMap<Competence, List<Document>>();

		Document doc1 = new Document("doc1", "", "", null, null);
		doc1.setId("0");
		Document doc2 = new Document("doc2", "", "", null, null);
		doc1.setId("1");
		Document doc3 = new Document("doc3", "", "", null, null);
		doc1.setId("2");
		Acquisition acquis1 = new Acquisition(owner, doc1, comp1, null);
		List<Acquisition> acquis = new ArrayList<Acquisition>();
		acquis.add(new Acquisition(owner, doc1, comp1, null));
		acquis.add(new Acquisition(owner, doc2, comp1, null));
		acquis.add(new Acquisition(owner, doc3, comp2, null));
		acquis.add(new Acquisition(owner, doc3, comp1, null));

		Iterator<Competence> item = setCompetence.iterator();
		while (item.hasNext()) {
			map.put(item.next(), new ArrayList<Document>());
		}

		for (int h = 0; h < acquis.size(); h++) {
			List<Document> listDocument = map
					.get(acquis.get(h).getCompetence());
			listDocument.add(acquis.get(h).getDocument());
			map.put(acquis.get(h).getCompetence(), listDocument);
		}

		return map;
	}

	private List<Resume> getFakeResumes(Owner owner) {
		List<Resume> listResume = new ArrayList<Resume>();
		Resume tmp = new Resume(owner, null);
		tmp.setId(0);
		Resume tmp2 = new Resume(owner, null);
		tmp2.setId(1);
		Resume tmp3 = new Resume(owner, null);
		tmp3.setId(2);
		listResume.add(tmp);
		listResume.add(tmp2);
		listResume.add(tmp3);
		return listResume;
	}

	private Map<Competence, List<Document>> getMapCompetence(Owner owner) {
		List<Degree> listDegrees = owner.getDegrees();
		Set<Competence> setCompetence = new HashSet<Competence>();

		Map<Competence, List<Document>> map = new HashMap<Competence, List<Document>>();

		for (int i = 0; i < listDegrees.size(); i++) {
			setCompetence.addAll(listDegrees.get(i).getCompetences());
		}

//##		AcquisitionDAO acquisDao = new AcquisitionDAO();
//##		List<Acquisition> acquis = acquisDao.findByOwner(owner);
		
		//## TODO injection
		IMetierUIServices metierUIServices = null ;
		List<Acquisition> acquis = metierUIServices.findAcquisitionByOwner(owner);
		
		Iterator<Competence> item = setCompetence.iterator();
		while (item.hasNext()) {
			map.put(item.next(), new ArrayList<Document>());
		}
		
		for (int h = 0; h < acquis.size(); h++) {

			List<Document> listDocument = map
					.get(acquis.get(h).getCompetence()) != null ? map
					.get(acquis.get(h).getCompetence())
					: new ArrayList<Document>();

			listDocument.add(acquis.get(h).getDocument());
			map.put(acquis.get(h).getCompetence(), listDocument);
		}

		return map;
	}

	public ManageConnectForm getConnect() {
		return connect;
	}

	public void setConnect(ManageConnectForm connect) {
		this.connect = connect;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
