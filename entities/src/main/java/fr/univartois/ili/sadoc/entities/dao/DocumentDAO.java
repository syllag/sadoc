package fr.univartois.ili.sadoc.entities.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.classes.Document;

public class DocumentDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	private DocumentDAO(){}
	
	public static void create(Document document) {
		em.getTransaction().begin();
		em.persist(document);
		em.getTransaction().commit();
	}

	public static Document findById(int id) {
		return em.find(Document.class, id);
	}

	public static void update(Document document) {
		em.getTransaction().begin();
		em.merge(document);
		em.getTransaction().commit();
	}

	public static void delete(Document document) {
		em.getTransaction().begin();
		em.remove(document);
		em.getTransaction().commit();
	}
}
