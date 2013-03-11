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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Degree;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageConnectForm;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

/**
 * @author Damien Wattiez <Damien Wattiez at gmail.com>
 * 
 */
public class ManageConnect extends ActionSupport implements SessionAware,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * form contains connect
	 */
	private ManageConnectForm connect;
	private Map<String, Object> session;

	private HttpServletRequest request;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	public String execute() {
		if (session.get("mail") != null) {
			return SUCCESS;
		}

		if (connect == null) {
			session.put("incorrect", "");
			return INPUT;
		}

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

			owner = metierUIServices.findOwnerByEmailAndPassword(
					connect.getEmail(), hashString.toString());

		} catch (NoSuchAlgorithmException e) {
		}

		/**
		 * if empty
		 */
		if (owner == null) {
			session.put("incorrect", "Password ou mail incorrect");
			return INPUT;
		}

		owner.setPassword(null);

		session.put("owner", owner);
		/**
		 * session.put("listResume", getFakeResumes(owner));
		 */

		
		//session.put("mapCompetence", getMapCompetence(owner));
		session.put("mapCompetence", new HashSet<Competence>());
		/**
		 * session.put("mapCompetence", getFakeMapCompetence(owner));
		 */

		session.put("mail", owner.getMail());
		return SUCCESS;
	}

	private Map<Competence, List<Document>> getMapCompetence(Owner owner) {
		List<Degree> listDegrees = owner.getDegrees();
		Set<Competence> setCompetence = new HashSet<Competence>();

		Map<Competence, List<Document>> map = new HashMap<Competence, List<Document>>();

		for (int i = 0; i < listDegrees.size(); i++) {
			setCompetence.addAll(listDegrees.get(i).getCompetences());
		}

		List<Acquisition> acquis = metierUIServices
				.findAcquisitionByOwner(owner);

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

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
