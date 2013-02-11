package fr.univartois.ili.sadoc.metier.ui.services;

import java.util.List;

import fr.univartois.ili.sadoc.metier.ui.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ui.vo.Competence;
import fr.univartois.ili.sadoc.metier.ui.vo.Document;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;

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
		return ownerDAO.findById(id);
	}

	public void createOwner(Owner owner) {
		ownerDAO.create(owner);		
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
		ownerDAO.update(owner);		
	}

	public Owner findOwnerByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Acquisition> findAcquisitionByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	public Owner findOwnerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateDocument(Document document) {
		// TODO Auto-generated method stub
		
	}

	public Resume findResumeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
