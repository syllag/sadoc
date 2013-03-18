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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.dao.services.IAcquisitionDAO;
import fr.univartois.ili.sadoc.dao.services.ICertificateDAO;
import fr.univartois.ili.sadoc.dao.services.IDocumentDAO;
import fr.univartois.ili.sadoc.dao.services.IOwnerWSDAO;
import fr.univartois.ili.sadoc.dao.services.ISignatureDAO;
import fr.univartois.ili.sadoc.metier.ws.utils.Mapper;
import fr.univartois.ili.sadoc.metier.ws.vo.Acquisition;
import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;
import fr.univartois.ili.sadoc.metier.ws.vo.Signature;

@Service
public class MetierWSServices implements IMetierWSServices {
	
	@Autowired
	private IAcquisitionDAO acquisitionDAO;
	@Autowired
	private ICertificateDAO certificateDAO;
	@Autowired
	private IDocumentDAO documentDAO;
	@Autowired
	private IOwnerWSDAO ownerWSDAO;
	@Autowired
	private ISignatureDAO signatureDAO;

	@Override
	public void createAcquisition(Acquisition acquisition) {
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.create(acqui);
	}

	// XXX utile???
	public void createCertificate(Certificate certificate) throws SQLException, IOException {
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper.certificateVOToCertificateDO(certificate);
		certificateDAO.create(certif);
	}

	@Override
	public void createDocument(Document document) {
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
		documentDAO.create(doc);
	}

	@Override
	public void createOwnerWS(Owner owner) {
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

	@Override
	public void createSignature(Signature signature) {
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper.signatureVOToSignatureDO(signature);
		signatureDAO.create(sign);
	}

	@Override
	public void updateAcquisition(Acquisition acquisition) {
		fr.univartois.ili.sadoc.dao.entities.Acquisition acqui = Mapper.acquisitionVOToAcquisitionDO(acquisition);
		acquisitionDAO.update(acqui);
	}
	
	@Override
	public void updateDocument(Document document) {
		// fr.univartois.ili.sadoc.dao.entities.Document docOld =
		// Mapper.documentVOToDocumentDO(findDocumentById(document.getId()));
		// fr.univartois.ili.sadoc.dao.entities.Document docNew =
		// Mapper.documentVOToDocumentDO(document);
		// docNew.setId(docOld.getId());
		// documentDAO.update(docNew);
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
		documentDAO.update(doc);
	}

	// XXX utile???
	public void updateCertificate(Certificate certificate) throws SQLException,
			IOException {
		fr.univartois.ili.sadoc.dao.entities.Certificate certif = Mapper.certificateVOToCertificateDO(certificate);
		certificateDAO.update(certif);
	}

	@Override
	public void updateOwnerWS(Owner owner) {
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
		fr.univartois.ili.sadoc.dao.entities.Signature sign = Mapper.signatureVOToSignatureDO(signature);
		signatureDAO.update(sign);
	}

	@Override
	public Document findDocumentById(long id) {
		Document document = Mapper.documentDOToDocumentVO(documentDAO.findById(id));
		return document;
	}

	@Override
	public List<Document> findDocumentByOwner(Owner owner) {
		List<Document> documents = new ArrayList<Document>();
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			List<fr.univartois.ili.sadoc.dao.entities.Document> docs = documentDAO.findByOwnerWS(ownerWS);
			for (fr.univartois.ili.sadoc.dao.entities.Document doc : docs) {
				documents.add(Mapper.documentDOToDocumentVO(doc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return documents;
	}
	
	@Override
	public Owner findOwnerByDocument(Document document) {
		Owner owner = new Owner();
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
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
	
	@Override
	public Owner findOwnerByMail(String mail) {
		Owner owner = null;
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
	public Certificate findCertificateByOwner(Owner owner) {
		Certificate certificate = null;
		fr.univartois.ili.sadoc.dao.entities.OwnerWS ownerWS;
		List<fr.univartois.ili.sadoc.dao.entities.Certificate> certifs = new ArrayList<fr.univartois.ili.sadoc.dao.entities.Certificate>();
		try {
			ownerWS = Mapper.ownerVOToOwnerDO(owner);
			certifs = certificateDAO.findByOwner(ownerWS);
			for (fr.univartois.ili.sadoc.dao.entities.Certificate certif : certifs) {
				if (certif.getDateValidity().after(new Date())) {
					certificate = Mapper.certificateDOToCertificateVO(certif);
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

	@Override
	public List<Certificate> findCertificatesByOwner(Owner owner) {
		List<Certificate> certificates = new ArrayList<Certificate>();
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
	
	// XXX utile???
	public Map<Owner, List<Acquisition>> findOwnerAndAcquisitionByDocumentId(
			int documentId) {
		Map<Owner, List<Acquisition>> res = new HashMap<Owner, List<Acquisition>>();
		Document document = findDocumentById(documentId);
		Owner owner = findOwnerByDocument(document);
		List<Acquisition> acquisitions = findAcquisitionsByDocument(document);
		res.put(owner, acquisitions);
		return res;
	}

	public List<Acquisition> findAcquisitionsByDocument(Document document){
		fr.univartois.ili.sadoc.dao.entities.Document doc = Mapper.documentVOToDocumentDO(document);
		List<Acquisition> a = new ArrayList<Acquisition>();
		List<fr.univartois.ili.sadoc.dao.entities.Acquisition> acqs = acquisitionDAO.findAcquisitionByDocument(doc);
		for(fr.univartois.ili.sadoc.dao.entities.Acquisition acq : acqs){
			a.add(Mapper.acquisitionDOToAcquisitionVO(acq));
		}
		return a;
	}
	
	@Override
	public Acquisition findAcquisitionByAcronym(String idItem) {
		Acquisition acquiqition = new Acquisition();
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
			out.write(doc.getP7s());
			out.close();
			in = new FileInputStream(new File("/tmp/authenticate.p7s"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

}
