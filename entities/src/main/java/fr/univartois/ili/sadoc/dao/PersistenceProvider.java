package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class PersistenceProvider {

	private static EntityManager em = null;
	
	public static EntityManager getEntityManager(){
		if (em == null){
			setProvider("sadocjpa");
		}
		return em;
	}
	
	public static void setProvider(String persistenceUnit){
		em = Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}
	
	public static void removeProvider(){
		em = null;
	}

}
