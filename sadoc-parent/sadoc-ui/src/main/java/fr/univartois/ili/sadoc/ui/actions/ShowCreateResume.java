package fr.univartois.ili.sadoc.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.services.IMetierUIServices;
import fr.univartois.ili.sadoc.ui.utils.ContextFactory;

public class ShowCreateResume extends ActionSupport implements SessionAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Competence> listCompetences = new ArrayList<Competence>();
	private Map<String, Object> session;
	private HttpServletRequest request;
	private IMetierUIServices metierUIServices = ContextFactory.getContext().getBean(IMetierUIServices.class) ;
	
	public String execute() {
//		long idOwner = (Integer) session.get("id");
//		Owner owner = metierUIServices.findOwnerById(idOwner);
//		for (Acquisition acquisition : metierUIServices.findAcquisitionByOwner(owner)){
//			listCompetences.add(acquisition.getCompetence());
//		}
//		request.setAttribute("listCompetences", listCompetences);
		
		request.setAttribute("listCompetences", getFakeReferentiels());
		return SUCCESS;
	}

	
	private List<Referentiel> getFakeReferentiels() {
		List<Referentiel> list = new ArrayList<Referentiel>();
		
		// Referentiel
		Referentiel ref = new Referentiel("REF_C2i", "C2i2e", "Référentiel C2i", "http://www.c2i.fr");
		list.add(ref);
		
		// Domaines
		Domaine domaineA = new Domaine("A", "Compétences générales liées à l’exercice du métier pour le C2i2e", ref);
		Domaine domaineB = new Domaine("B", "Compétences nécessaires à l’intégration des TICE dans sa pratique", ref);
		ref.getDomaines().add(domaineA);
		ref.getDomaines().add(domaineB);
		
		// Competences
		Competence competenceA1 = new Competence("A.1", "Maîtrise de l’environnement numérique professionnel", domaineA);
		Competence competenceA2 = new Competence("A.2", "Développement des compétences pour la formation tout au long de la vie", domaineA);
		domaineA.getCompetences().add(competenceA1);
		domaineA.getCompetences().add(competenceA2);
		Competence competenceB1 = new Competence("B.1", "Travail en réseau avec l’utilisation des outils de travail collaboratif", domaineB);
		domaineB.getCompetences().add(competenceB1);
		
		// Items
		Item A1item1 = new Item("A.1.1", "Item 1 de A1");
		Item A1item2 = new Item("A.1.2", "Item 2 de A1");
		Item A1item3 = new Item("A.1.3", "Item 3 de A1");
		competenceA1.getItems().add(A1item1);
		competenceA1.getItems().add(A1item2);
		competenceA1.getItems().add(A1item3);
		Item A2item1 = new Item("A.2.1", "Item 1 de A2");
		Item A2item2 = new Item("A.2.2", "Item 2 de A2");
		competenceA2.getItems().add(A2item1);
		competenceA2.getItems().add(A2item2);
		Item B1item1 = new Item("B.1.1", "Item 1 de B1");
		competenceB1.getItems().add(B1item1);
		
		
		// Referentiel
		ref = new Referentiel("REF_CLES", "Cles anglais", "Référentiel Cles anglais", "http://www.cles_anglais.fr");
		list.add(ref);
		
		// Domaines
		domaineA = new Domaine("A", "Niveaux introductif et intermédiaire", ref);
		domaineB = new Domaine("B", "Niveaux seuil et avancé", ref);
		ref.getDomaines().add(domaineA);
		ref.getDomaines().add(domaineB);
		
		// Competences
		competenceA1 = new Competence("A.1", "Niveau introductif", domaineA);
		domaineA.getCompetences().add(competenceA1);
		competenceB1 = new Competence("B.1", "Niveau seuil", domaineB);
		domaineB.getCompetences().add(competenceB1);
		
		// Items
		A1item1 = new Item("A.1 Ecouter", "Je peux comprendre des mots familiers...");
		A1item2 = new Item("A.1 Lire", "Je peux comprendre des noms familiers...");
		A1item3 = new Item("A.1 Parler", "Je peux communiquer, de façon simple...");
		competenceA1.getItems().add(A1item1);
		competenceA1.getItems().add(A1item2);
		competenceA1.getItems().add(A1item3);
		B1item1 = new Item("B.1 Ecouter", "Je peux comprendre les points essentiels...");
		competenceB1.getItems().add(B1item1);
		
		return list;
	}
	
	
	public List<Competence> getListCompetences() {
		return listCompetences;
	}

	/**
	 * @return the metierUIServices
	 */
	public IMetierUIServices getMetierUIServices() {
		return metierUIServices;
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;		
	}
}
