package fr.univartois.ili.sadoc.dao.services;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.dao.entities.Competence;
import fr.univartois.ili.sadoc.dao.entities.Domaine;

@Repository("CompetenceDAO")
@Transactional
public class CompetenceDAOImpl extends AbstractCommunDAO  implements ICompetenceDAO{
	
	
	public CompetenceDAOImpl() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Competence findCompetenceById(long id) {
		return entityManager.find(Competence.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createCompetence(Competence competence) {		
		entityManager.persist(competence);	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Competence> findCompetenceByDomaine(Domaine domaine) {
		TypedQuery<Competence> query = entityManager.createQuery(
				"SELECT c FROM Competence c WHERE c.domaine = :domaine",
				Competence.class);
		query.setParameter("domaine", domaine);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Competence findByAcronym(String acronym) {
		TypedQuery<Competence> query = entityManager.createQuery(
				"SELECT c FROM Competence c WHERE c.codeCompetence = :acronym",
				Competence.class);
		query.setParameter("acronym", acronym);
		return query.getSingleResult();
	}

}
