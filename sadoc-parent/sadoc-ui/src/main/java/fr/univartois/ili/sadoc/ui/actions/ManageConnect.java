package fr.univartois.ili.sadoc.ui.actions;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Degree;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.ui.form.ManageConnectForm;
import fr.univartois.ili.sadoc.ui.utils.Connection;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ManageConnect extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private ManageConnectForm connect;
	private Map<String, Object> session;

	private IMetierUIServices metierUIServices = ContextFactory.getContext()
			.getBean(IMetierUIServices.class);

	@Override
	public String execute() throws NoSuchAlgorithmException {
		Owner owner = Connection.getUser(session);

		if (owner != null) {
			return SUCCESS;
		}
		if (connect == null) {
			return INPUT;
		}

		owner = metierUIServices.findOwnerByEmailAndPassword(
				connect.getEmail(),
				Connection.encryptPassword(connect.getPassword()));
		if (owner == null) {
			addActionError("Mot de passe ou email incorrect");
			return INPUT;
		}

		owner.setPassword(null);

		// TODO remove when will be useless
		session.put("mail", owner.getMail());
		session.put("owner", owner);

		session.put("mapCompetence", new HashSet<Competence>());

		// session.put("listResume", getFakeResumes(owner));
		// session.put("mapCompetence", getMapCompetence(owner));
		// session.put("mapCompetence", getFakeMapCompetence(owner));

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

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}
}
