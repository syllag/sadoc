/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ui.utils;

import java.util.HashMap;
import java.util.Map;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
public class Mapper {

	/**
	 * Identity map to keep relational Object model 
	 */
	private static Map<Object, Object> identityMap = new HashMap<Object,Object>();
	
	
	/**
	 * This method return a fr.univartois.ili.sadoc.metier.ui.vo.Owner, the business object
	 * corresponding to the fr.univartois.sadoc.dao.entities.Owner in database
	 * 
	 * @param owner 
	 * 		the owner in database
	 * @return
	 * 		the owner in business layer
	 */
	public static Owner getOwnerFromEntities(fr.univartois.ili.sadoc.dao.entities.Owner owner){
		Owner newOwner = (Owner) alreadyExist(owner);
		if(newOwner == null){
			newOwner = new Owner();
			identityMap.put(owner, newOwner);
		}
		
		newOwner.setId(owner.getId());
		newOwner.setFirstName(owner.getFirstName());
		newOwner.setLastName(owner.getLastName());
		newOwner.setMail(owner.getMail());
		newOwner.setPassword(null);
		newOwner.setAddress(owner.getAddress());
		newOwner.setZipCode(owner.getZipCode());
		newOwner.setTown(owner.getTown());
		newOwner.setPhone(owner.getPhone());
		newOwner.setIdOwnerWs(owner.getIdOwnerWs());
			
		return newOwner;
	}
	
	
	/**
	 * This method return a fr.univartois.sadoc.dao.entities.Owner, the database object
	 * corresponding to the fr.univartois.ili.sadoc.metier.ui.vo.Owner in business layer
	 * 
	 * @param owner 
	 * 		the owner in business layer
	 * @return
	 * 		the owner in database
	 */
	public static fr.univartois.ili.sadoc.dao.entities.Owner getOwnerFromVO(Owner owner){
		fr.univartois.ili.sadoc.dao.entities.Owner newOwner = (fr.univartois.ili.sadoc.dao.entities.Owner) alreadyExist(owner);
		if(newOwner == null){
			newOwner = new fr.univartois.ili.sadoc.dao.entities.Owner();
			newOwner.setPassword(owner.getPassword());
			identityMap.put(owner, newOwner);
		}
		
		if(owner.getPassword() != null)
			newOwner.setPassword(owner.getPassword());
		
		newOwner.setFirstName(owner.getFirstName());
		newOwner.setLastName(owner.getLastName());
		newOwner.setMail(owner.getMail());
		newOwner.setAddress(owner.getAddress());
		newOwner.setZipCode(owner.getZipCode());
		newOwner.setTown(owner.getTown());
		newOwner.setPhone(owner.getPhone());
		newOwner.setIdOwnerWs(owner.getIdOwnerWs());
		
		return newOwner;
	}
	
	//TODO : Penser au mapper pour les objets récupérer du WS : package client.webservice.tools
	
	/**
	 * Check in identity map if the object already exist
	 * 
	 * @param object
	 * @return
	 */
	private static Object alreadyExist(Object object){
		Object retour = null;

		for(Map.Entry<Object, Object> mapEntry : identityMap.entrySet()){
			if(object == mapEntry.getKey()){
				retour = mapEntry.getValue();
				break;
			}
			if(object == mapEntry.getValue()){
				retour = mapEntry.getKey();
				break;
			}
		}
		return retour;
	}
}
