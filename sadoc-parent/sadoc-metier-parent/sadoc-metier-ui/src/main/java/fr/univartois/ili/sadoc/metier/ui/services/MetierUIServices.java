package fr.univartois.ili.sadoc.metier.ui.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univartois.ili.sadoc.metier.ui.utils.Mapper;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.sadoc.dao.services.IOwnerDAO;

@Component
public class MetierUIServices implements IMetierUIServices{
	
	@Autowired
	private IOwnerDAO ownerDAO ;
	

	/**
	 * @return the ownerDAO
	 */
	public IOwnerDAO getOwnerDAO() {
		return ownerDAO;
	}

	/**
	 * @param ownerDAO the ownerDAO to set
	 */
	public void setOwnerDAO(IOwnerDAO ownerDAO) {
		this.ownerDAO = ownerDAO;
	}

	//TODO appeler les mapper VO-DO avant de retourner les objets
	
	public Document findDocumentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	public Owner findOwnerById(int id) {
		fr.univartois.sadoc.dao.entities.Owner owner = ownerDAO.findOwnerById(id);
		Owner ownerVO = null;
		if(owner != null)
			ownerVO = Mapper.getOwnerFromEntities(owner); 
		return ownerVO;
	}

	public void createOwner(Owner owner) {
		fr.univartois.sadoc.dao.entities.Owner ownerDO = Mapper.getOwnerFromVO(owner);
		ownerDAO.createOwner(ownerDO);
	}

	public Competence findCompetenceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createCompetence(Competence competence) {
		// TODO Auto-generated method stub
		
	}

	public void createAcquisition(Acquisition acquisition) {
		// TODO Auto-generated method stub
		
	}

	public List<Acquisition> findAcquisitionByDocument(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createResume(Resume resume) {
		// TODO Auto-generated method stub
		
	}

	public void updateOwner(Owner owner) {
		fr.univartois.sadoc.dao.entities.Owner ownerDO = Mapper.getOwnerFromVO(owner);
		ownerDAO.updateOwner(ownerDO);		
	}

	public Owner findOwnerByEmailAndPassword(String email, String password) {
		fr.univartois.sadoc.dao.entities.Owner ownerDO = ownerDAO.findOwnerByEmailAndPassword(email, password);
		Owner owner = null;
		if(ownerDO != null){
			owner = Mapper.getOwnerFromEntities(ownerDO);
		}
		return owner;
	}

	public List<Acquisition> findAcquisitionByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	public Owner findOwnerByEmail(String email) {
		fr.univartois.sadoc.dao.entities.Owner ownerDO = ownerDAO.findOwnerByEmail(email);
		Owner owner = null;
		if(ownerDO != null){
			owner = Mapper.getOwnerFromEntities(ownerDO);
		}
		return owner;
	}

	public void updateDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	public Resume findResumeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
