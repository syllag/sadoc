package fr.univartois.ili.sadoc.ui.utils;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;

public class ResumeUtil {

	/**
	 * Generate a Map structured like a Tree with Referentiels, Domaines, Competences and
	 * items from the resume
	 * @param resume
	 * @return a Map containing data from the resume
	 */
	public static Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> generateMap (Resume resume){
		Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = new TreeMap <Referentiel, Map <Domaine, Map <Competence, List<Item>>>>();
		refWithDoms = generateFromItems(resume, refWithDoms);
		refWithDoms = generateFromCompetences(resume, refWithDoms);
		refWithDoms = generateFromDomaines(resume, refWithDoms);
		refWithDoms = generateFromReferentiels(resume, refWithDoms);
		return refWithDoms;		
	}
	
	/**
	 * fill a copy of the map with data from the list of items and return it
	 * @param resume
	 * @param map
	 * @return a copy of the map with data from the list of items
	 */
	private static Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> generateFromItems (Resume resume, Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> map ){
		Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = new TreeMap<Referentiel, Map <Domaine, Map <Competence, List<Item>>>>(map);
		for(Item it : resume.getItems()){
			Competence cp = it.getCompetence();
			Domaine dom = cp.getDomaine();
			Referentiel ref = dom.getReferentiel();
			if(! refWithDoms.containsKey(ref)){
				refWithDoms.put(ref, new TreeMap<Domaine, Map <Competence, List<Item>>>());
			}
			Map <Domaine, Map <Competence, List<Item>>> domWithComps = refWithDoms.get(ref);
			if(! domWithComps.containsKey(dom)){
				domWithComps.put(dom, new TreeMap<Competence, List<Item>>());
			}
			Map <Competence, List<Item>> compWithItems = domWithComps.get(dom);
			if(! compWithItems.containsKey(cp)){
				compWithItems.put(cp, new ArrayList<Item>());
			}
			compWithItems.get(cp).add(it);		
		}
		return refWithDoms;		
	}

	/**
	 * fill a copy of the map with data from the list of Competences and return it
	 * @param resume
	 * @param map
	 * @return a copy of the map with data from the list of Competences
	 */
	private static Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> generateFromCompetences (Resume resume, Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> map ){
		Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = new TreeMap<Referentiel, Map <Domaine, Map <Competence, List<Item>>>>(map);
		for(Competence cp : resume.getCompetences()){
			Domaine dom = cp.getDomaine();
			Referentiel ref = dom.getReferentiel();
			if(! refWithDoms.containsKey(ref)){
				refWithDoms.put(ref, new TreeMap<Domaine, Map <Competence, List<Item>>>());
			}
			Map <Domaine, Map <Competence, List<Item>>> domWithComps = refWithDoms.get(ref);
			if(! domWithComps.containsKey(dom)){
				domWithComps.put(dom, new TreeMap<Competence, List<Item>>());
			}
			Map <Competence, List<Item>> compWithItems = domWithComps.get(dom);
			if(! compWithItems.containsKey(cp)){
				compWithItems.put(cp, new ArrayList<Item>());
			}	
		}
		return refWithDoms;
	}
	
	/**
	 * fill a copy of the map with data from the list of Domaines and return it
	 * @param resume
	 * @param map
	 * @return a copy of the map with data from the list of Domaines
	 */
	private static Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> generateFromDomaines (Resume resume, Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> map ){
		Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = new TreeMap<Referentiel, Map <Domaine, Map <Competence, List<Item>>>>(map);
		for(Domaine dom : resume.getDomaines()){
			Referentiel ref = dom.getReferentiel();
			if(! refWithDoms.containsKey(ref)){
				refWithDoms.put(ref, new TreeMap<Domaine, Map <Competence, List<Item>>>());
			}
			Map <Domaine, Map <Competence, List<Item>>> domWithComps = refWithDoms.get(ref);
			if(! domWithComps.containsKey(dom)){
				domWithComps.put(dom, new TreeMap<Competence, List<Item>>());
			}	
		}
		return refWithDoms;
	}
	
	/**
	 * fill a copy of the map with data from the list of Referentiels and return it
	 * @param resume
	 * @param map
	 * @return a copy of the map with data from the list of Referentiels
	 */
	private static Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> generateFromReferentiels (Resume resume, Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> map ){
		Map <Referentiel, Map <Domaine, Map <Competence, List<Item>>>> refWithDoms = new TreeMap<Referentiel, Map <Domaine, Map <Competence, List<Item>>>>(map);
		for(Referentiel ref : resume.getReferentiels()){
			if(! refWithDoms.containsKey(ref)){
				refWithDoms.put(ref, new TreeMap<Domaine, Map <Competence, List<Item>>>());
			}
		}
		return refWithDoms;
	}
	
}
