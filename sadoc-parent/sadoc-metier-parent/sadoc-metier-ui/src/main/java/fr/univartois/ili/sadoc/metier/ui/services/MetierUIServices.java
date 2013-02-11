package fr.univartois.ili.sadoc.metier.ui.services;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;
import fr.univartois.sadoc.dao.services.IOwnerDAO;

public class MetierUIServices implements IMetierUIServices{
	
	private IOwnerDAO ownerDAO ;
	
	//TODO appeler les mapper VO-DO avant de retourner les objets 

	public Document findDocumentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	public Owner findOwnerById(int id) {
		//TODO mapping
		return ownerDAO.findOwnerById(id);
	}

	public void createOwner(Owner owner) {
		//TODO mapping
		ownerDAO.createOwner(owner);		
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
		//TODO mapping
		ownerDAO.updateOwner(owner);		
	}

	public Owner findOwnerByEmailAndPassword(String email, String password) {
		//TODO mapping
		return ownerDAO.findOwnerByEmailAndPassword(email, password);
	}

	public List<Acquisition> findAcquisitionByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	public Owner findOwnerByEmail(String email) {
		//TODO mapping
		return ownerDAO.findOwnerByEmail(email);
	}

	public void updateDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	public Resume findResumeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
