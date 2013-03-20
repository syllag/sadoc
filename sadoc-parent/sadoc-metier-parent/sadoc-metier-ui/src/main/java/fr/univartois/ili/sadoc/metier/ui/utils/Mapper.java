/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.univartois.ili.sadoc.metier.commun.services.IMetierCommunServices;
import fr.univartois.ili.sadoc.metier.commun.vo.Competence;
import fr.univartois.ili.sadoc.metier.commun.vo.Domaine;
import fr.univartois.ili.sadoc.metier.commun.vo.Item;
import fr.univartois.ili.sadoc.metier.commun.vo.Referentiel;
import fr.univartois.ili.sadoc.metier.ui.vo.Owner;
import fr.univartois.ili.sadoc.metier.ui.vo.Resume;

/**
 * @author Alan Delahaye <alan.delahayee@gmail.com>
 *
 */
public class Mapper {

	/**
	 * Identity map to keep relational Object model 
	 */
	private static Map<Object, Object> identityMap = new HashMap<Object,Object>();

	private static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextMetierUI.xml");  
	private static IMetierCommunServices metier = applicationContext.getBean(IMetierCommunServices.class);

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
		Set<Object> stack = new HashSet<Object>();
		return getOwnerFromEntities(owner, stack);
	}
	
	private static Owner getOwnerFromEntities(fr.univartois.ili.sadoc.dao.entities.Owner owner, Set<Object> stack){
		Owner newOwner = (Owner) alreadyExist(owner);
		if(newOwner == null){
			newOwner = new Owner();
			identityMap.put(owner, newOwner);
		} else if (stack.contains(newOwner)) {
			return newOwner;
		}
		stack.add(newOwner);

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

		List<Resume> resumes = new ArrayList<Resume>();
		for (fr.univartois.ili.sadoc.dao.entities.Resume resume : owner.getResumes()) {
			resumes.add(getResumeFromEntities(resume, stack));
		}
		newOwner.setResumes(resumes);
		
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
	public static fr.univartois.ili.sadoc.dao.entities.Owner getOwnerFromVO(Owner owner) {
		Set<Object> stack = new HashSet<Object>();
		return getOwnerFromVO(owner, stack);
	}
	
	private static fr.univartois.ili.sadoc.dao.entities.Owner getOwnerFromVO(Owner owner, Set<Object> stack){
		fr.univartois.ili.sadoc.dao.entities.Owner newOwner = (fr.univartois.ili.sadoc.dao.entities.Owner) alreadyExist(owner);
		if(newOwner == null){
			newOwner = new fr.univartois.ili.sadoc.dao.entities.Owner();
			newOwner.setPassword(owner.getPassword());
			identityMap.put(owner, newOwner);
		} else if (stack.contains(newOwner)) {
			return newOwner;
		}
		stack.add(newOwner);

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

		List<fr.univartois.ili.sadoc.dao.entities.Resume> resumes = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Resume>();
		for (Resume resume : owner.getResumes()) {
			resumes.add(getResumeFromVO(resume, stack));
		}
		newOwner.setResumes(resumes);
		
		return newOwner;
	}

	/**
	 * This method return a fr.univartois.ili.sadoc.metier.ui.vo.Resume, the business object
	 * corresponding to the fr.univartois.sadoc.dao.entities.Resume in database
	 * 
	 * @param resume 
	 * 		the resume in database
	 * @return
	 * 		the resume in business layer
	 */
	public static Resume getResumeFromEntities(fr.univartois.ili.sadoc.dao.entities.Resume resume) {
		Set<Object> stack = new HashSet<Object>();
		return getResumeFromEntities(resume, stack);
	}
	
	private static Resume getResumeFromEntities(fr.univartois.ili.sadoc.dao.entities.Resume resume, Set<Object> stack){
		Resume newResume = (Resume) alreadyExist(resume);
		if(newResume == null){
			newResume = new Resume();
			identityMap.put(resume, newResume);
		} else if (stack.contains(newResume)) {
			return newResume;
		}
		stack.add(newResume);

		newResume.setId(resume.getId());
		newResume.setOwner(getOwnerFromEntities(resume.getOwner(), stack));
		newResume.setReferentiels(getObjetsFromIds(resume.getReferentiels(),findByIdReferentiel));
		newResume.setDomaines(getObjetsFromIds(resume.getDomaines(),findByIdDomaine));
		newResume.setCompetences(getObjetsFromIds(resume.getCompetences(),findByIdCompetence));
		newResume.setItems(getObjetsFromIds(resume.getItems(),findByIdItem));
		return newResume;
	}

	/**
	 * This method return a fr.univartois.sadoc.dao.entities.Owner, the database object
	 * corresponding to the fr.univartois.ili.sadoc.metier.ui.vo.Owner in business layer
	 * 
	 * @param Resume 
	 * 		the resume in business layer
	 * @return
	 * 		the resume in database
	 */
	public static fr.univartois.ili.sadoc.dao.entities.Resume getResumeFromVO(Resume resume){
		Set<Object> stack = new HashSet<Object>();
		return getResumeFromVO(resume, stack);
	}
	
	private static fr.univartois.ili.sadoc.dao.entities.Resume getResumeFromVO(Resume resume, Set<Object> stack){
		fr.univartois.ili.sadoc.dao.entities.Resume newResume = (fr.univartois.ili.sadoc.dao.entities.Resume) alreadyExist(resume);
		if(newResume == null){
			newResume = new fr.univartois.ili.sadoc.dao.entities.Resume();
			identityMap.put(resume, newResume);
		} else if (stack.contains(newResume)) {
			return newResume;
		}
		stack.add(newResume);
		
		newResume.setOwner(getOwnerFromVO(resume.getOwner(), stack));
		newResume.setReferentiels(getIdsFromObjets(resume.getReferentiels(),findByIdReferentiel));
		newResume.setDomaines(getIdsFromObjets(resume.getDomaines(),findByIdDomaine));
		newResume.setCompetences(getIdsFromObjets(resume.getCompetences(),findByIdCompetence));
		newResume.setItems(getIdsFromObjets(resume.getItems(),findByIdItem));
		return newResume;
	}

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

	interface FindById<T> {
		public T getById(long id);
		public long getId(T element);
	}

	private static FindById<Referentiel> findByIdReferentiel = new FindById<Referentiel>() {
		@Override
		public Referentiel getById(long id) {
			return metier.findReferentielById(id);
		}
		@Override
		public long getId(Referentiel element) {
			return element.getId();
		}
	};

	private static FindById<Domaine> findByIdDomaine = new FindById<Domaine>() {
		@Override
		public Domaine getById(long id) {
			return metier.findDomaineById(id);
		}
		@Override
		public long getId(Domaine element) {
			return element.getId();
		}
	};

	private static FindById<Competence> findByIdCompetence = new FindById<Competence>() {
		@Override
		public Competence getById(long id) {
			return metier.findCompetenceById(id);
		}
		@Override
		public long getId(Competence element) {
			return element.getId();
		}
	};

	private static FindById<Item> findByIdItem = new FindById<Item>() {
		@Override
		public Item getById(long id) {
			return metier.findItemById(id);
		}
		@Override
		public long getId(Item element) {
			return element.getId();
		}
	};

	private static <T> List<T> getObjetsFromIds(List<Long> ids, FindById<T> finder) {
		List<T> res = new ArrayList<T>();
		for(Long id : ids) {
			res.add(finder.getById(id));
		}
		return res;
	}
	
	private static <T> List<Long> getIdsFromObjets(List<T> objects, FindById<T> finder) {
		List<Long> res = new ArrayList<Long>();
		for(T object : objects) {
			res.add(finder.getId(object));
		}
		return res;
	}
	
}
