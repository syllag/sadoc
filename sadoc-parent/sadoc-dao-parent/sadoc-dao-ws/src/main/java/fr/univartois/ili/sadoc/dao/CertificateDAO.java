package fr.univartois.ili.sadoc.dao;

import java.security.acl.Owner;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.univartois.ili.sadoc.constante.Request;
import fr.univartois.ili.sadoc.dao.entities.Certificate;
import fr.univartois.ili.sadoc.dao.services.ICertificateDAO;
import fr.univartois.ili.sadoc.dao.services.IWebServiceDAO;

@Service("certificateDAO")
@Transactional
public class CertificateDAO extends AbstractWebServiceDAO implements IWebServiceDAO<Certificate>,ICertificateDAO {

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
	public List<Certificate> findByOwner(Owner owner) {
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
