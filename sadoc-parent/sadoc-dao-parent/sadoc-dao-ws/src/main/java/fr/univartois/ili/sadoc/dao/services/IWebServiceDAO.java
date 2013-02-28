package fr.univartois.ili.sadoc.dao.services;


/**
 * Interface centralized all DAO basics methods
 * 
 * @param <T> The entity concerned by the DAO
 */
public interface IWebServiceDAO<T>{
	
	/**
	 * Persist an Object
	 * @param entity the entity to persist
	 */
	void create(T entity);
	
	/**
	 * Return the entity referenced by the id 
	 * @param id The id of the object
	 * @return The entity
	 */
	T findById(long id);
	
	/**
	 * Delete a entity in the DB
	 * @param entity The entity to delete
	 */
	void delete(T entity);
	
	/**
	 * Update the given entity
	 * @param entity The entity to update
	 */
	void update(T entity);
	
	/**
	 * Refresh the entity given
	 * @param entity The entity to refresh
	 */
	void refresh(T entity);
}
