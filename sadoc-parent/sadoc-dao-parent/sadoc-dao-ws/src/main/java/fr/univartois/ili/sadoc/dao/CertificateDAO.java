package fr.univartois.ili.sadoc.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Certificate;
import fr.univartois.ili.sadoc.dao.entities.OwnerWS;
import fr.univartois.ili.sadoc.dao.services.ICertificateDAO;

@Repository("certificateDAO")
@Transactional
public class CertificateDAO extends AbstractWebServiceDAO implements ICertificateDAO {

	public CertificateDAO() {
		super();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(Certificate certificate) {
		entityManager.persist(certificate);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Certificate findById(long id) {
		return entityManager.find(Certificate.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Certificate> findByOwner(OwnerWS owner) {
		final TypedQuery<Certificate> query;
		query = entityManager.createQuery(Request.FIND_IN_CERTIFICATE_BY_OWNER,
				Certificate.class);
		query.setParameter("owner", owner);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Certificate certificate) {
		entityManager.merge(certificate);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Certificate certificate) {
		entityManager.remove(certificate);
	}

	@Override
	public void refresh(Certificate entity) {
		entityManager.refresh(entity);
	}
}
