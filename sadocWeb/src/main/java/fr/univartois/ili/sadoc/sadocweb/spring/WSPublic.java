package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Owner;

public interface WSPublic {

	public Owner createOwner(String nom, String prenom, String mail)
			throws Exception;

	public byte[] signDocument(byte[] doc, String name, Owner owner,
			Competence[] competence);
	
	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence);

	public void createCertificate(Owner utilisateur);

	public List<Certificate> getCertificate(Owner utilisateur);

}
