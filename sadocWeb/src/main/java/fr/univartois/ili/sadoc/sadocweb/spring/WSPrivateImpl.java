package fr.univartois.ili.sadoc.sadocweb.spring;

import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class WSPrivateImpl implements WSPrivate {

	public boolean verifyDocument(Byte[] doc, Certificate certificat,
			Owner utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	public Document[] importDocument(Owner utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	public Competence[] importCompetences(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

}
