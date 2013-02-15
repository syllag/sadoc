package fr.univartois.ili.sadoc.dao.services;

import fr.univartois.ili.sadoc.dao.entities.OwnerWS;


public interface IOwnerDAO {

	/**
	 * Find a Owner with this email address
	 * @param mail the owner email
	 * @return The owner
	 */
	OwnerWS findByMail(String mail);

}
