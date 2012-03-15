package fr.univartois.ili.sadoc.actions;

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

import fr.univartois.ili.sadoc.Form.ManageConnectForm;
import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Degree;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

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

		if (connect == null) {
			session.put("incorrect", "");
			return INPUT;
		}
		OwnerDAO odao = new OwnerDAO();
		/*
		 * Owner owner = null; MessageDigest messageDigest; try { messageDigest
		 * = MessageDigest.getInstance("MD5" ); byte[] p =
		 * messageDigest.digest(connect.getPassword().getBytes());
		 * connect.setPassword(p.toString()); owner =
		 * odao.findOwner(connect.getEmail(), p.toString());
		 * 
		 * } catch (NoSuchAlgorithmException e) {}
		 */
		Owner owner = odao.findOwner(connect.getEmail(), connect.getPassword());

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

		session.put("mapCompetence", getMapCompetence(owner));

		return SUCCESS;
	}

	private Map<Competence, List<Document>> getMapCompetence(Owner owner) {
		List<Degree> listDegrees = owner.getDegrees();
		Set<Competence> setCompetence = new HashSet<Competence>();

		Map<Competence, List<Document>> map = new HashMap<Competence, List<Document>>();

		for (int i = 0; i < listDegrees.size(); i++) {
			setCompetence.addAll(listDegrees.get(i).getCompetences());
		}

		AcquisitionDAO acquisDao = new AcquisitionDAO();
		List<Acquisition> acquis = acquisDao.findByOwner(owner);

		Iterator<Competence> item = setCompetence.iterator();
		while (item.hasNext()) {
			map.put(item.next(), new ArrayList<Document>());
		}

		for (int h = 0; h < acquis.size(); h++) {
			List<Document> listDocument= map.get(acquis.get(h).getCompetence());
			listDocument.add(acquis.get(h).getDocument());
			map.put(acquis.get(h).getCompetence(),listDocument);
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
