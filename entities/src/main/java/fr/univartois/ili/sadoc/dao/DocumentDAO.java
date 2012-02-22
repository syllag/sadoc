package fr.univartois.ili.sadoc.dao;

import javax.persistence.EntityManager;

import fr.univartois.ili.sadoc.entities.Document;

public class DocumentDAO {

	private static final EntityManager em = PersistenceProvider.getEntityManager();
	
	public DocumentDAO(){}
	
	public static void create(Document document) {
		em.getTransaction().begin();
		em.persist(document);
		em.getTransaction().commit();
	}

	public static Document findById(int id) {
		Document document = em.find(Document.class, id);
		return document;
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
