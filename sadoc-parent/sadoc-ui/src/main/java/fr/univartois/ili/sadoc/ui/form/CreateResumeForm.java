package fr.univartois.ili.sadoc.ui.form;

import java.util.ArrayList;
import java.util.List;

import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;

public class CreateResumeForm {

	private String[] choiceReferentiels = null;
	private String[] choiceDomaines = null;
	private String[] choiceCompetences = null;
	private String[] choiceItems = null;

	public CreateResumeForm() {
	}

	public String[] getChoiceReferentiels() {
		return choiceReferentiels;
	}

	public List<Referentiel> getChoiceReferentiels(IMetierCommunServices metier) {
		return getChoice(findReferentiel, metier, choiceReferentiels);
	}

	public void setChoiceReferentiels(String[] choiceReferentiels) {
		this.choiceReferentiels = choiceReferentiels;
	}

	public String[] getChoiceDomaines() {
		return choiceDomaines;
	}

	public List<Domaine> getChoiceDomaines(IMetierCommunServices metier) {
		return getChoice(findDomaine, metier, choiceDomaines);
	}

	public void setChoiceDomaines(String[] choiceDomaines) {
		this.choiceDomaines = choiceDomaines;
	}

	public String[] getChoiceCompetences() {
		return choiceCompetences;
	}

	public List<Competence> getChoiceCompetences(IMetierCommunServices metier) {
		return getChoice(findCompetence, metier, choiceCompetences);
	}

	public void setChoiceCompetences(String[] choiceCompetences) {
		this.choiceCompetences = choiceCompetences;
	}

	public String[] getChoiceItems() {
		return choiceItems;
	}

	public List<Item> getChoiceItems(IMetierCommunServices metier) {
		return getChoice(findItem, metier, choiceItems);
	}

	public void setChoiceItems(String[] choiceItems) {
		this.choiceItems = choiceItems;
	}

	/**
	 * Generic interface for code factorization to get elements of type
	 * Referentiel, Domaine, Competence , ...
	 * 
	 * @param <T>
	 *            Referentiel, Domaine, Competence or Item
	 */
	private interface Finder<T> {

		/**
		 * @return new list of type <T>
		 */
		public List<T> newList();

		/**
		 * @param metier
		 *            metier used to ask elements of type T
		 * @param id
		 *            id of the element of type T
		 * @return the element requested
		 */
		public T find(IMetierCommunServices metier, long id);
	}

	private Finder<Referentiel> findReferentiel = new Finder<Referentiel>() {
		@Override
		public List<Referentiel> newList() {
			return new ArrayList<Referentiel>();
		}

		@Override
		public Referentiel find(IMetierCommunServices metier, long id) {
			return metier.findReferentielById(id);
		}
	};

	private Finder<Domaine> findDomaine = new Finder<Domaine>() {
		@Override
		public List<Domaine> newList() {
			return new ArrayList<Domaine>();
		}

		@Override
		public Domaine find(IMetierCommunServices metier, long id) {
			return metier.findDomaineById(id);
		}
	};

	private Finder<Competence> findCompetence = new Finder<Competence>() {
		@Override
		public List<Competence> newList() {
			return new ArrayList<Competence>();
		}

		@Override
		public Competence find(IMetierCommunServices metier, long id) {
			return metier.findCompetenceById(id);
		}
	};

	private Finder<Item> findItem = new Finder<Item>() {
		@Override
		public List<Item> newList() {
			return new ArrayList<Item>();
		}

		@Override
		public Item find(IMetierCommunServices metier, long id) {
			return metier.findItemById(id);
		}
	};

	/**
	 * @param finder
	 *            finder to get elements of type <T>
	 * @param metier
	 *            used by the finder to get elements of type <T>
	 * @param listIds
	 *            table of ids of elements of type <T>
	 * @return objects of type <T> corresponding to the ids in listIds
	 */
	private <T> List<T> getChoice(Finder<T> finder,
			IMetierCommunServices metier, String[] listIds) {
		List<T> list = finder.newList();
		if (listIds != null) {
			int size = listIds.length;
			for (int i = 0; i < size; ++i) {
				if (!"false".equals(listIds[i])) {
					list.add(finder.find(metier, Long.parseLong(listIds[i])));
				}
			}
		}
		return list;
	}
}
