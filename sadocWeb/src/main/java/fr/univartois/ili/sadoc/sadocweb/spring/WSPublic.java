package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;

public interface WSPublic {

	public Owner createOwner(String nom, String prenom, String mail)
			throws Exception;

	public Byte[] signDocument(Byte[] doc, String name, Owner owner,
			Competence[] competence);
	
	public Byte[] signDocument(Byte[] doc, String name, Certificate certificat,
			Competence[] competence);

	public void createCertificate(Owner utilisateur);

	public List<Certificate> getCertificate(Owner utilisateur);

}