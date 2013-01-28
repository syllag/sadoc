package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.domaine.Certificate;
import fr.univartois.ili.sadoc.domaine.Owner;
import fr.univartois.ili.sadoc.constante.Request;

@Service("certificateDAO")
@Transactional
public class CertificateDAO {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "sadocjpa")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CertificateDAO() {
		entityManager = PersistenceProvider.getEntityManager();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Certificate certificate) {
		entityManager.persist(certificate);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Certificate findById(int id) {
		return entityManager.find(Certificate.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Certificate> findByOwner(Owner owner) {
		final TypedQuery<Certificate> query;
		query = entityManager.createQuery(Request.FIND_IN_CERTIFICATE_BY_OWNER,
				Certificate.class);
		query.setParameter("owner", owner);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Certificate certificate) {
		entityManager.merge(certificate);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Certificate certificate) {
		entityManager.remove(certificate);
	}
}
