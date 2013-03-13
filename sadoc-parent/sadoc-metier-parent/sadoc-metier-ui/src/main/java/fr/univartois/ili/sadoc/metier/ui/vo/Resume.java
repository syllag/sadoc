package fr.univartois.ili.sadoc.metier.ui.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class Resume implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private Owner owner;

	private List<Referentiel> referentiels = new ArrayList<Referentiel>();
	private List<Domaine> domaines = new ArrayList<Domaine>();
	private List<Competence> competences = new ArrayList<Competence>();
	private List<Item> items = new ArrayList<Item>();

	/************************************************/

	public Resume() {
	}

	public Resume(Owner owner, List<Competence> competences) {
		this.owner = owner;
	}

	/************************************************/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<Referentiel> getReferentiels() {
		return referentiels;
	}

	public void setReferentiels(List<Referentiel> referentiels) {
		this.referentiels = referentiels;
	}

	public List<Domaine> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domaine> domaines) {
		this.domaines = domaines;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	// TODO to remove when fake will be useless
	public static Resume getFake() {
		Resume res = new Resume();

		// Referentiel
		Referentiel ref = new Referentiel("REF_C2i", "C2i2e",
				"Référentiel C2i", "http://www.c2i.fr");

		// Domaines
		Domaine domaineA = new Domaine(
				"A",
				"Compétences générales liées à l'exercice du métier pour le C2i2e",
				ref);
		Domaine domaineB = new Domaine(
				"B",
				"Compétences nécessaires à l'intégration des TICE dans sa pratique",
				ref);
		ref.getDomaines().add(domaineA);
		ref.getDomaines().add(domaineB);

		// Competences
		Competence competenceA1 = new Competence("A.1",
				"Maîtrise de l'environnement numérique professionnel", domaineA);
		Competence competenceA2 = new Competence(
				"A.2",
				"Développement des compétences pour la formation tout au long de la vie",
				domaineA);
		domaineA.getCompetences().add(competenceA1);
		domaineA.getCompetences().add(competenceA2);
		// No competences for B
		res.getDomaines().add(domaineB);
		
		// Items
		Item A1item1 = new Item("A.1.1", "Item 1 de A1");
		Item A1item2 = new Item("A.1.2", "Item 2 de A1");
		Item A1item3 = new Item("A.1.3", "Item 3 de A1");
		competenceA1.getItems().add(A1item1);
		competenceA1.getItems().add(A1item2);
		competenceA1.getItems().add(A1item3);
		res.getItems().add(A1item1);
		res.getItems().add(A1item2);
		res.getItems().add(A1item3);
		// No item for A2
		res.getCompetences().add(competenceA2);

		// Referentiel
		ref = new Referentiel("REF_CLES", "Cles anglais",
				"Référentiel Cles anglais", "http://www.cles_anglais.fr");
		// No domaines for ref
		res.getReferentiels().add(ref);

		return res;
	}
}
