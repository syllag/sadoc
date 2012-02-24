package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;


@Endpoint
public class WSStub {
	
	@Resource(name="wsPrivate")
	private  WSPrivate wsPrivate;
	@Resource(name="wsPublic")
	private  WSPublic wsPublic;
	
	//private final OwnerDAO ownerDAO = new OwnerDAO();
	
	

	@PayloadRoot(localPart = "createOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Owner createOwner(@RequestPayload CreateOwnerRequest request)
			throws Exception {
		//Utilisation d'un logger à la place
		//System.out.println("Debug createOwner : "+request.getLastName()+request.getFirstName()+request.getMail());
		return wsPublic.createOwner(request.getLastName(), request.getFirstName(), request.getMail());
	}

	@PayloadRoot(localPart = "signDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public byte[] signDocument(@RequestPayload SignDocumentRequest request) {
		return wsPublic.signDocument(request.getDoc(), request.getName(), request.getOwner(), request.getCompetence());
	}


	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence) {
		//Cette méthode devrait-elle renvoyer un tableau vide plutôt que null ?
		return null;
	}

	@PayloadRoot(localPart = "createCertificateRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public void createCertificate(@RequestPayload CreateCertificateRequest request) {
		wsPublic.createCertificate(request.getOwner());
	}

	public List<Certificate> getCertificate(Owner utilisateur) {
		return null;
	}

	@PayloadRoot(localPart = "verifyDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public boolean verifyDocument(@RequestPayload Byte[] doc,
			@RequestPayload Document document, @RequestPayload Owner utilisateur) {
		return wsPrivate.verifyDocument(doc, document, utilisateur);
	}

	@PayloadRoot(localPart = "getDocumentInformationsRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Map<Owner, List<Competence>> getDocumentInformations(
			@RequestPayload int documentId) {
		return wsPrivate.getDocumentInformations(documentId);
	}

	@PayloadRoot(localPart = "importDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public List<Document> importDocument(@RequestPayload Owner owner) {
		return wsPrivate.importDocument(owner);
	}

	@PayloadRoot(localPart = "importCompetencesRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public List<Competence> importCompetences(@RequestPayload Document document) {
		return wsPrivate.importCompetences(document);
	}

	@PayloadRoot(localPart = "getDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Document getDocument(@RequestPayload  int id) {
		return wsPrivate.getDocument(id);
	}
	
//	public WSPrivate getWsPrivate() {
//		return wsPrivate;
//	}
//
//	public WSPublic getWsPublic() {
//		return wsPublic;
//	}

//	public OwnerDAO getOwnerDAO() {
//		return ownerDAO;
//	}

}
