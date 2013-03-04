package fr.univartois.ili.sadoc.metier.commun.services;

import java.util.ArrayList;
import java.util.List;

import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;
import fr.univartois.ili.sadoc.dao.services.IDomaineDAO;
import fr.univartois.ili.sadoc.dao.services.IItemDAO;
import fr.univartois.ili.sadoc.dao.services.IReferentielDAO;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.commun.utils.Mapper;

/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public class MetierCommunServices implements IMetierCommunServices {
	private IItemDAO itemDAO;
	private ICompetenceDAO competenceDAO;
	private IDomaineDAO domaineDAO;
	private	IReferentielDAO referentielDAO;
		
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

}
