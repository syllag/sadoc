package fr.univartois.ili.sadoc.metier.commun.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;
import fr.univartois.ili.sadoc.dao.entities.Item;
import fr.univartois.ili.sadoc.dao.entities.Referentiel;
import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;


/**
 * 
 *
 */
@Component
public class Mapper {
	private static Map<Long,fr.univartois.ili.sadoc.metier.commun.vo.Competence> listeCompetences=new HashMap<Long,fr.univartois.ili.sadoc.metier.commun.vo.Competence>();
	private static Map<Long,fr.univartois.ili.sadoc.metier.commun.vo.Domaine> listeDomaines=new HashMap<Long,fr.univartois.ili.sadoc.metier.commun.vo.Domaine>();
	private static Map<Long,fr.univartois.ili.sadoc.metier.commun.vo.Referentiel> listeReferentiels=new HashMap<Long,fr.univartois.ili.sadoc.metier.commun.vo.Referentiel>();
	private static Map<Long,fr.univartois.ili.sadoc.metier.commun.vo.Item> listeItems=new HashMap<Long,fr.univartois.ili.sadoc.metier.commun.vo.Item>();
	
	@Autowired
	private static ICompetenceDAO competenceDAO;
	@Autowired
	private static IDomaineDAO domaineDAO;
	@Autowired
	private static IItemDAO itemDAO;
	@Autowired
	private static IReferentielDAO referentielDAO;
	
	public static fr.univartois.ili.sadoc.metier.commun.vo.Competence getCompetenceFromEntity(Competence c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		if(listeCompetences.containsKey(c.getId()))
			return listeCompetences.get(c.getId());
		
		fr.univartois.ili.sadoc.metier.commun.vo.Competence res = new fr.univartois.ili.sadoc.metier.commun.vo.Competence();
		List<fr.univartois.ili.sadoc.metier.commun.vo.Item> lItems = new ArrayList<fr.univartois.ili.sadoc.metier.commun.vo.Item>();
		Domaine d=c.getDomaine();
		List<Item> lItem=c.getItems();
		
		
		for(Item tmpI:lItem){
			lItems.add(listeItems.get(getItemFromEntity(tmpI)));
		}
		
		res.setCodeCompetence(c.getCodeCompetence());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setDomaine(getDomainFromEntity(d));
		res.setItems(lItems);
		
		return res;
	}
	
	public static fr.univartois.ili.sadoc.metier.commun.vo.Domaine getDomainFromEntity(Domaine c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		if(listeDomaines.containsKey(c.getId()))
			return listeDomaines.get(c.getId());
		
		fr.univartois.ili.sadoc.metier.commun.vo.Domaine res = new fr.univartois.ili.sadoc.metier.commun.vo.Domaine();
		Referentiel r=c.getReferentiel();
		List<fr.univartois.ili.sadoc.metier.commun.vo.Competence> lComp=new ArrayList<fr.univartois.ili.sadoc.metier.commun.vo.Competence>();
		
		listeDomaines.put(c.getId(),res);
		
		for(Competence tmpC:c.getCompetences()){
			lComp.add(getCompetenceFromEntity(tmpC));
		}
		
		res.setCodeDomaine(c.getCodeDomaine());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setCompetences(lComp);
		res.setReferentiel(getReferentielFromEntity(r));
		
		return res;
	}
	
	public static fr.univartois.ili.sadoc.metier.commun.vo.Item getItemFromEntity(Item c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		if(listeItems.containsKey(c.getId()))
			return listeItems.get(c.getId());
		
		fr.univartois.ili.sadoc.metier.commun.vo.Item res = new fr.univartois.ili.sadoc.metier.commun.vo.Item();
		
		res.setCodeItem(c.getCodeItem());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setEmpreinte(c.getEmpreinte());
		res.setPoids(c.getPoids());
		res.setType(c.getType());
		
		listeItems.put(c.getId(),res);
		
		return res;
	}
	
	public static fr.univartois.ili.sadoc.metier.commun.vo.Referentiel getReferentielFromEntity(Referentiel c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		if(listeReferentiels.containsKey(c.getId()))
			return listeReferentiels.get(c.getId());
		
		List<fr.univartois.ili.sadoc.metier.commun.vo.Domaine> ld=new ArrayList<fr.univartois.ili.sadoc.metier.commun.vo.Domaine>();
		fr.univartois.ili.sadoc.metier.commun.vo.Referentiel res = new fr.univartois.ili.sadoc.metier.commun.vo.Referentiel();
			
		listeReferentiels.put(c.getId(),res);
		
		for(Domaine d:c.getDomaines()){
			ld.add(getDomainFromEntity(d));
		}
		
		res.setCodeReferentiel(c.getCodeReferentiel());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setName(c.getName());
		res.setSeuil(c.getSeuil());
		res.setUrl(c.getUrl());
		res.setDomaines(ld);
		
		
		
		return res;
	}
	
	public static Competence getCompetenceFromVO(fr.univartois.ili.sadoc.metier.commun.vo.Competence c) {
		if(c == null)
			throw new IllegalArgumentException();
		Competence res = competenceDAO.findCompetenceById(c.getId());
		List<Item> lItem = new ArrayList<Item>();
		
		for(fr.univartois.ili.sadoc.metier.commun.vo.Item i:c.getItems()){
			lItem.add(getItemFromVO(i));
		}
		
		res.setCodeCompetence(c.getCodeCompetence());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setDomaine(getDomainFromVO(c.getDomaine()));
		res.setItems(lItem);
		
		return res;
	}
	
	public static Domaine getDomainFromVO(fr.univartois.ili.sadoc.metier.commun.vo.Domaine c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		Domaine res = domaineDAO.findDomaineById(c.getId());
		List<Competence> lComp = new ArrayList<Competence>();
		
		for(fr.univartois.ili.sadoc.metier.commun.vo.Competence co:c.getCompetences()){
			lComp.add(getCompetenceFromVO(co));
		}
		
		res.setCodeDomaine(c.getCodeDomaine());
		res.setDescription(c.getDescription());
		res.setReferentiel(getReferentielFromVO(c.getReferentiel()));
		res.setCompetences(lComp);
		
		return res;
	}
	
	public static Item getItemFromVO(fr.univartois.ili.sadoc.metier.commun.vo.Item c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		Item res = itemDAO.findItemById(c.getId());
		
		res.setCodeItem(c.getCodeItem());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setEmpreinte(c.getEmpreinte());
		res.setPoids(c.getPoids());
		res.setType(c.getType());
		res.setCompetence(getCompetenceFromVO(c.getCompetence()));
		
		return res;
	}
	
	public static Referentiel getReferentielFromVO(fr.univartois.ili.sadoc.metier.commun.vo.Referentiel c) {
		if(c == null)
			throw new IllegalArgumentException();
		
		Referentiel res = referentielDAO.findReferentielById(c.getId());
		
		List<Domaine> lDom=new ArrayList<Domaine>();
		
		for(fr.univartois.ili.sadoc.metier.commun.vo.Domaine d:c.getDomaines()){
			lDom.add(getDomainFromVO(d));
		}
		
		res.setCodeReferentiel(c.getCodeReferentiel());
		res.setDescription(c.getDescription());
		res.setId(c.getId());
		res.setName(c.getName());
		res.setSeuil(c.getSeuil());
		res.setUrl(c.getUrl());
		res.setDomaines(lDom);
		
		return res;
	}
}
