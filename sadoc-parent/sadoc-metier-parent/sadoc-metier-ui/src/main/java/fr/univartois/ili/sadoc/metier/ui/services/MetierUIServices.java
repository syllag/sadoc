package fr.univartois.ili.sadoc.metier.ui.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univartois.ili.sadoc.dao.services.IOwnerDAO;
import fr.univartois.ili.sadoc.dao.services.IResumeDAO;
import fr.univartois.ili.sadoc.metier.ui.utils.Mapper;
import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;

@Component
public class MetierUIServices implements IMetierUIServices{
	
	@Autowired
	private IOwnerDAO ownerDAO ;
	
	@Autowired
	private IResumeDAO resumeDAO;

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
	@Override
	public void createOwner(Owner owner) {
		ownerDAO.createOwner(Mapper.getOwnerFromVO(owner));
	}
	@Override
	public void updateOwner(Owner owner) {
		ownerDAO.updateOwner(Mapper.getOwnerFromVO(owner));		
	}
	
	@Override
	public Owner findOwnerById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Owner owner = ownerDAO.findOwnerById(id);
		Owner ownerVO = null;
		if(owner != null)
			ownerVO = Mapper.getOwnerFromEntities(owner); 
		return ownerVO;
	}
	
	@Override
	public Owner findOwnerByEmailAndPassword(String email, String password) {
		fr.univartois.ili.sadoc.dao.entities.Owner ownerDO = ownerDAO.findOwnerByEmailAndPassword(email, password);
		Owner owner = null;
		if(ownerDO != null){
			owner = Mapper.getOwnerFromEntities(ownerDO);
		}
		return owner;
	}
	
	@Override
	public Owner findOwnerByEmail(String email) {
		fr.univartois.ili.sadoc.dao.entities.Owner ownerDO = ownerDAO.findOwnerByEmail(email);
		Owner owner = null;
		if(ownerDO != null){
			owner = Mapper.getOwnerFromEntities(ownerDO);
		}
		return owner;
	}
	
	@Override
	public void createDocument(Document document) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void updateDocument(Document document) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Document findDocumentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCompetence(Competence competence) {
		// TODO Auto-generated method stub
	}

	@Override
	public Competence findCompetenceById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAcquisition(Acquisition acquisition) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Acquisition> findAcquisitionByDocument(Document document) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Acquisition> findAcquisitionByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createResume(Resume resume) {
		fr.univartois.ili.sadoc.dao.entities.Resume res = Mapper.getResumeFromVO(resume);
		resumeDAO.createResume(Mapper.getResumeFromVO(resume));
		resume.setId(res.getId());
		Mapper.updateReferenceVO(resume, res);
	}

	@Override
	public void updateResume(Resume resume) {
		resumeDAO.updateResume(Mapper.getResumeFromVO(resume));
	}
	
	@Override
	public Resume findResumeById(long id) {
		fr.univartois.ili.sadoc.dao.entities.Resume resume = resumeDAO.findResumeById(id);
		Resume resumeVO = null;
		if(resume != null)
			resumeVO = Mapper.getResumeFromEntities(resume); 
		return resumeVO;
	}

	@Override
	public void removeResume(Resume resume) {
		resumeDAO.removeResume(Mapper.getResumeFromVO(resume));
	}
}
