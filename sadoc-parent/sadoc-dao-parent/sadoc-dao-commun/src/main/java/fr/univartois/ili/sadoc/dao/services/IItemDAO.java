package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Item;

/**
 * @author Noureddine Kasri < kasrinoureddine@gmail.com >
 *
 */
public interface IItemDAO {
	/**
	 * Ask the Item identified by id of the Database
	 * 
	 * @param id
	 * @return Item
	 * return the corresponding item
	 */
	Item findItemById(long id);

	/**
	 * Insert the item in DB
	 * 
	 * @param item that is the item to create 
	 */
	void createItem(Item item);
	
	/**
	 * Ask the list of Item by Competence
	 * 
	 * @param competence
	 * @return List<Item>
	 */
	List<Item> findItemByCompetence(Competence competence);

}
