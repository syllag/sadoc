package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import fr.univartois.ili.sadoc.dao.CertificateDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.dao.SignatureDAO;
import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;
import fr.univartois.ili.sadoc.entities.Signature;

public class WSPublicImpl implements WSPublic {

	public void createOwner(String lastName, String firstName, String mail)
			throws Exception {
		Owner owner = new Owner(firstName, lastName, mail);
		OwnerDAO.create(owner);
	}

	public Byte[] signDocument(Byte[] doc, Certificate certificate,
			Competence[] competence) {
		// Creation du document ? Recuperation du document ? WTF
		Document document = new Document();
		for (Competence comp : competence) {
			Signature signature = new Signature(document,
					certificate.getOwner(), comp, certificate);
			SignatureDAO.create(signature);
		}
		// Signature du document
		// Retourner le tableau de Byte
		return null;
	}

	public void createCertificate(Owner owner) {
		// Generation clefs publiques & prives
		Certificate certificate = new Certificate("publicKey", "privateKey", owner);
		CertificateDAO.create(certificate);
	}

	public Certificate[] getCertificate(Owner owner) {
		List<Certificate> certificates = CertificateDAO.findByOwner(owner);
		return (Certificate[]) certificates.toArray();
	}

}
