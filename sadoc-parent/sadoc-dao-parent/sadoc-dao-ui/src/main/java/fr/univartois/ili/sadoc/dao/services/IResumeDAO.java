package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Resume;

public interface IResumeDAO {

	/**
	 * Ask the resume identified by id of the DB
	 * 
	 * @param id
	 * @return Resume
	 * return the corresponding resume
	 */
	Resume findResumeById(long id);
	
	/**
	 * Insert the resume in DB
	 * 
	 * @param resume that is the resume to create 
	 */
	void createResume(Resume resume);
	
	/**
	 * Update the owner in DB
	 * 
	 * @param owner 
	 * the owner is the owner to update 
	 */
	void updateResume(Resume resume);
}
