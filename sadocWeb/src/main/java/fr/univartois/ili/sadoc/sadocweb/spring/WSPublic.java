package fr.univartois.ili.sadoc.sadocweb.spring;

import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Owner;

public interface WSPublic {

	public void createOwner(String nom, String prenom, String mail)
			throws Exception;

	public Byte[] signDocument(Byte[] doc, Certificate certificat,
			Competence[] competence);

	public void createCertificate(Owner utilisateur);

	public Certificate[] getCertificate(Owner utilisateur);

}
