package fr.univartois.ili.sadoc.metier.commun.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;
import fr.univartois.ili.sadoc.metier.commun.utils.Mapper;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;

/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
@Service("metierCommunServices")
public class MetierCommunServices implements IMetierCommunServices {
	private static final String ACRONYM_REGEXP = "(\\d+)?:(\\d+)?:(\\d+)?:(\\d+)?";
	private static final String INTEGER_REGEXP = "\\d*";
	private IItemDAO itemDAO;
	private ICompetenceDAO competenceDAO;
	private IDomaineDAO domaineDAO;
	private	 IReferentielDAO referentielDAO;
		
	@Override
	public Item findItemById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Item itemDO = itemDAO.findItemById(id);
		return Mapper.getItemFromEntity(itemDO);
	}

	@Override
	public List<Item> findItemByCompetence(Competence competence) {
		fr.univartois.ili.sadoc.dao.entities.Competence competenceDO = Mapper.getCompetenceFromVO(competence);
		List<fr.univartois.ili.sadoc.dao.entities.Item> listItemsDo = itemDAO.findItemByCompetence(competenceDO);
		List<Item> listItemsVO = new ArrayList<Item>();
		for (fr.univartois.ili.sadoc.dao.entities.Item itemDO : listItemsDo) {
			listItemsVO.add(Mapper.getItemFromEntity(itemDO));
		}
		
		return listItemsVO;
	}

	@Override
	public void createItem(Item item) {
		fr.univartois.ili.sadoc.dao.entities.Item itemDO = Mapper.getItemFromVO(item);
		itemDAO.createItem(itemDO);
	}

	@Override
	public Competence findCompetenceById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Competence competenceDO = competenceDAO.findCompetenceById(id);
		return Mapper.getCompetenceFromEntity(competenceDO);
	}

	@Override
	public void createCompetence(Competence competence) {
		fr.univartois.ili.sadoc.dao.entities.Competence competenceDO = Mapper.getCompetenceFromVO(competence);
		competenceDAO.createCompetence(competenceDO);
	}

	@Override
	public List<Competence> findCompetenceByDomaine(Domaine domaine) {
		fr.univartois.ili.sadoc.dao.entities.Domaine domaineDO = Mapper.getDomainFromVO(domaine);
		List<fr.univartois.ili.sadoc.dao.entities.Competence> listCompetencesDo = competenceDAO.findCompetenceByDomaine(domaineDO);
		List<Competence> listCompetencesVO = new ArrayList<Competence>();
		for (fr.univartois.ili.sadoc.dao.entities.Competence competenceDO : listCompetencesDo) {
			listCompetencesVO.add(Mapper.getCompetenceFromEntity(competenceDO));
		}
		
		return listCompetencesVO;
	}

	@Override
	public Domaine findDomaineById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Domaine domaineDO = domaineDAO.findDomaineById(id);
		return Mapper.getDomainFromEntity(domaineDO);
	}

	@Override
	public void createDomaine(Domaine domaine) {
		fr.univartois.ili.sadoc.dao.entities.Domaine domaineDO = Mapper.getDomainFromVO(domaine);
		domaineDAO.createDomaine(domaineDO);
	}

	@Override
	public Referentiel findReferentielById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Referentiel referentielDO = referentielDAO.findReferentielById(id);
		return Mapper.getReferentielFromEntity(referentielDO);
	}

	@Override
	public List<Domaine> findDomaineByReferentiel(Referentiel referentiel) {
		fr.univartois.ili.sadoc.dao.entities.Referentiel referentielDO = Mapper.getReferentielFromVO(referentiel);
		List<fr.univartois.ili.sadoc.dao.entities.Domaine> listDomainesDo = domaineDAO.findDomaineByReferentiel(referentielDO);
		List<Domaine> listDomainesVO = new ArrayList<Domaine>();
		for (fr.univartois.ili.sadoc.dao.entities.Domaine domaineDO : listDomainesDo) {
			listDomainesVO.add(Mapper.getDomainFromEntity(domaineDO));
		}

		return listDomainesVO;
	}

	@Override
	public void createReferentiel(Referentiel referentiel) {
		fr.univartois.ili.sadoc.dao.entities.Referentiel referentielDO = Mapper.getReferentielFromVO(referentiel);
		referentielDAO.createReferentiel(referentielDO);
	}

	@Override
	public boolean isValideAcronym(String acronym) {
		boolean res=false;
		boolean dom=false;
		boolean comp=false;
		boolean item=false;
		int i=0;
		
		if(acronym==null || !acronym.matches(ACRONYM_REGEXP))
			throw new IllegalArgumentException();
		
		String decomp[]=new String[4];
		
		/* Nécessité d'utiliser une instance de Matcher car la méthode String.split() ne permet pas de récupérer des chaines vides */
		Matcher m=Pattern.compile(INTEGER_REGEXP).matcher(acronym);
		
		while(m.find() && i<4){
			decomp[i++]=m.group();
			System.out.println(i+" "+decomp[i-1]);
		}
		
		System.out.println("i = "+i);
		
		if(decomp[0].equals(""))
			return false;
		
		if(decomp[1].equals("")){
			if(!decomp[2].equals("") || !decomp[3].equals(""))
				return false;
		}else
			dom=true;
				
		if(decomp[2].equals("")){
			if(!decomp[3].equals(""))
				return false;
		}else
			comp=true;
		
		if(!decomp[3].equals("")){
			item=true;
		}
		
		res = res && (referentielDAO.findReferentielById(Long.parseLong(decomp[0])) != null);
		if(dom)
			res = res && (domaineDAO.findDomaineById(Long.parseLong(decomp[1])) != null);
		
		if(comp)
			res = res && (competenceDAO.findCompetenceById(Long.parseLong(decomp[2])) != null);
		
		if(item)
			res = res && (itemDAO.findItemById(Long.parseLong(decomp[3])) != null);
		
		return res;
	}

}
