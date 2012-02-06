package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceProvider {

	private static EntityManager em = null;
	
	public static EntityManager getEntityManager(){
		if (em == null){
			em = Persistence.createEntityManagerFactory("sadocjpa").createEntityManager();
		}
		return em;
	}

}
