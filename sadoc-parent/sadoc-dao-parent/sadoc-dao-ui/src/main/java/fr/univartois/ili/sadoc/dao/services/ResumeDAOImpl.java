package fr.univartois.ili.sadoc.dao.services;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Resume;

@Repository("ResumeDAO")
@Transactional
public class ResumeDAOImpl extends AbstractWebAppDAO implements IResumeDAO{

	public ResumeDAOImpl() {
		super();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Resume findResumeById(long id) {
		return entityManager.find(Resume.class,id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createResume(Resume resume) {
		entityManager.persist(resume);		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateResume(Resume resume) {
		entityManager.merge(resume);	
	}

	@Override
	public void removeResume(Resume resume) {
		entityManager.remove(resume);
	}

}
