package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

@Service("wsPublic")
public interface WSPublic {

	public Owner createOwner(String nom, String prenom, String mail)
			throws Exception;

	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Competence[] competence);
	
	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence);

	public void createCertificate(Owner utilisateur);

	public List<Certificate> getCertificate(Owner utilisateur);
	
	public Owner getOwner(String mail);
	
	public List<Document> getAllDocumentByOwner(Owner user);

}
