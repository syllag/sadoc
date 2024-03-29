package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.Owner;

/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface IOwnerDAO {
	
	
	/**
	 * Ask the owner identified by id of the DB
	 * 
	 * @param id
	 * @return Owner
	 * return the corresponding owner
	 */
	Owner findOwnerById(long id);
	
	
	/**
	 * Insert the owner in DB
	 * 
	 * @param owner that is the owner to create 
	 */
	void createOwner(Owner owner);
	
	/**
	 * Update the owner in DB
	 * 
	 * @param owner 
	 * the owner is the owner to update 
	 */
	void updateOwner(Owner owner);
	
	
	/**
	 * Ask the owner authenticate by email and password of the DB
	 * 
	 * @param email
	 * @param password
	 * @return Owner
	 * return the corresponding owner
	 */
	Owner findOwnerByEmailAndPassword(String email, String password);
	
	
	/**
	 * Ask the owner identified by email of the DB
	 * 
	 * @param email
	 * @return Owner
	 * return the corresponding owner
	 */
	Owner findOwnerByEmail(String email);

}
