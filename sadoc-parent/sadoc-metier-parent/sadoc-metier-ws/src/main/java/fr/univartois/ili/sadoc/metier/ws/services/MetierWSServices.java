package fr.univartois.ili.sadoc.metier.ws.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerWSDAO;
import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.metier.ws.utils.Mapper;
import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;
// TODO utiliser les interfaces
//import fr.univartois.ili.sadoc.dao.services.ICompetenceDAO;

public class MetierWSServices implements IMetierWSServices {

	public void createAcquisition(Acquisition acquisition) {
		AcquisitionDAO acquisitionDAO = new AcquisitionDAO();
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper
				.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.create(acqui);
	}

	// XXX utile???
	public void createCertificate(Certificate certificate) throws SQLException,
			IOException {
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper
				.certificateVOToCertificateDO(certificate);
		certificateDAO.create(certif);
	}

	public void createDocument(Document document) {
		DocumentDAO documentDAO = new DocumentDAO();
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper
				.documentVOToDocumentDO(document);
		documentDAO.create(doc);
	}

	public void createOwnerWS(Owner owner) {
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			ownerWSDAO.create(ownerWS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createSignature(Signature signature) {
		SignatureDAO signatureDAO = new SignatureDAO();
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper
				.signatureVOToSignatureDO(signature);
		signatureDAO.create(sign);
	}

	public void updateDocument(Document document) {
		DocumentDAO documentDAO = new DocumentDAO();
		// fr.univartois.ili.sadoc.dao.entities.Document docOld =
		// Mapper.documentVOToDocumentDO(findDocumentById(document.getId()));
		// fr.univartois.ili.sadoc.dao.entities.Document docNew =
		// Mapper.documentVOToDocumentDO(document);
		// docNew.setId(docOld.getId());
		// documentDAO.update(docNew);
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper
				.documentVOToDocumentDO(document);
		documentDAO.update(doc);
	}

	public void updateAcquisition(Acquisition acquisition) {
		AcquisitionDAO acquisitionDAO = new AcquisitionDAO();
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper
				.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.update(acqui);
	}

	// XXX utile???
	public void updateCertificate(Certificate certificate) throws SQLException,
			IOException {
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper
				.certificateVOToCertificateDO(certificate);
		certificateDAO.update(certif);
	}

	public void updateOwnerWS(Owner owner) {
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			ownerWSDAO.update(ownerWS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// XXX utile???
	public void updateSignature(Signature signature) {
		SignatureDAO signatureDAO = new SignatureDAO();
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper
				.signatureVOToSignatureDO(signature);
		signatureDAO.update(sign);
	}

	public Document findDocumentById(long id) {
		Document document = new Document();
		DocumentDAO documentDAO = new DocumentDAO();
		document = Mapper.documentDOToDocumentVO(documentDAO.findById(id));
		return document;
	}

	public Certificate findCertificateByOwner(Owner owner) {
		Certificate certificate = new Certificate();
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		List<fr.univartois.ili.sadoc.dao.entities.Certificate> certifs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Certificate>();
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			certifs = certificateDAO.findByOwner(ownerWS);
			for (fr.univartois.ili.sadoc.dao.entities.Certificate certif : certifs) {
				if (certif.getDateValidity().after(new Date())) {
					certificate = Mapper.certificateDOToCertificateVO(certif);
				} else {
					certificate = null;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return certificate;
	}

	public List<Certificate> findCertificatesByOwner(Owner owner) {
		List<Certificate> certificates = new ArrayList<Certificate>();
		CertificateDAO certificateDAO = new CertificateDAO();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		List<fr.univartois.ili.sadoc.dao.entities.Certificate> certifs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Certificate>();
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			certifs = certificateDAO.findByOwner(ownerWS);
			for (fr.univartois.ili.sadoc.dao.entities.Certificate certif : certifs) {
				certificates.add(Mapper.certificateDOToCertificateVO(certif));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return certificates;
	}

	public Owner findOwnerByDocument(Document document) {
		fr.univartois.ili.sadoc.dao.entities.Document doc = new fr.univartois.ili.sadoc.dao.entities.Document();
		doc = Mapper.documentVOToDocumentDO(document);
		Owner owner = new Owner();
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		try {
			owner = Mapper.ownerDOToOwnerVO(ownerWSDAO.findByDocument(doc));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return owner;
	}

	public List<Document> findDocumentByOwner(Owner owner) {
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS = new fr.univartois.ili.sadoc.dao.entities.OwnerWS();
		List<Document> documents = new ArrayList<Document>();
		DocumentDAO documentDAO = new DocumentDAO();
		List<fr.univartois.ili.sadoc.dao.entities.Document> docs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Document>();
		docs = documentDAO.findByOwnerWS(ownerWS);

		for (fr.univartois.ili.sadoc.dao.entities.Document doc : docs) {
			documents.add(Mapper.documentDOToDocumentVO(doc));
		}
		return documents;
	}

	public Owner findOwnerByMail(String mail) {
		Owner owner = null;
		OwnerWSDAO ownerWSDAO = new OwnerWSDAO();
		try {
			owner = Mapper.ownerDOToOwnerVO(ownerWSDAO.findByMail(mail));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return owner;
	}

	@Override
	public Competence findCompetenceByAcronym(String acronym) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Competence> findCompetencesByDocument(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Owner, List<Competence>> findOwnerAndCompetencesByDocumentId(
			int documentId) {
		Map<Owner, List<Competence>> res = new HashMap<Owner, List<Competence>>();
		Document document = findDocumentById(documentId);
		Owner owner = findOwnerByDocument(document);
		List<Competence> competences = findCompetencesByDocument(document);
		res.put(owner, competences);
		return res;
	}

	@Override
	public Acquisition findAcquisitionByAcronym(String idItem) {
		Acquisition acquiqition = new Acquisition();
		AcquisitionDAO acquisitionDAO = new AcquisitionDAO();
		acquiqition = Mapper.acquisitionDOToAcquisitionVO(acquisitionDAO
				.findByAcronym(idItem));
		return acquiqition;
	}

	@Override
	public FileInputStream getP7SFromDocument(long id) {
		Document doc = findDocumentById(id);
		if(doc == null) return null;
		FileOutputStream out;
		FileInputStream in = null;
		try {
			out = new FileOutputStream("/tmp/authenticate.p7s");
			out.write(doc.getPk7());
			out.close();
			in = new FileInputStream(new File("/tmp/authenticate.p7s"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	// XXX Plus nécessaire car pas Competence dans la BDws
	// public void createCompetence(Competence competence) {
	// ICompetenceDAO competenceDAO;
	// fr.univartois.ili.sadoc.dao.entities.Competence competence2 =
	// mapperCompetencesVOToCompetencesDO(competence);
	// competenceDAO.create(competence2);
	// }
	// public Competence findCompetenceByAcronym(String acronym) {
	// Competence competence = new Competence();
	// CompetenceDAO competenceDAO = new CompetenceDAO();
	// competence =
	// mapperOwnerDOToOwnerVO(competenceDAO.findByAcronym(acronym));
	// return competence;
	// }
	// public List<Competence> findCompetencesByDocument(Document document) {
	// List<Competence> competences = new ArrayList<Competence>();
	// List<CompetenceDAO> competencesDAO = new ArrayList<CompetenceDAO>();
	// competences =
	// mapperCompetencesDOToCompetencesVO(competencesDAO.findCompetencesByDocument(document));
	// return competences;
	// }

}
