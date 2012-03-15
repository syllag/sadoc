package fr.univartois.ili.sadoc.sadocweb.spring;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.univartois.ili.sadoc.entities.classes.Certificate;
import fr.univartois.ili.sadoc.entities.classes.Competence;
import fr.univartois.ili.sadoc.entities.classes.Document;
import fr.univartois.ili.sadoc.entities.classes.Owner;
import fr.univartois.ili.sadoc.entities.classes.Signature;
import fr.univartois.ili.sadoc.entities.dao.CertificateDAO;
import fr.univartois.ili.sadoc.entities.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.entities.dao.DocumentDAO;
import fr.univartois.ili.sadoc.entities.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.dao.PersistenceProvider;
import fr.univartois.ili.sadoc.entities.dao.SignatureDAO;

@Endpoint
public class WSStub {

	@Resource(name = "wsPrivate")
	private WSPrivate wsPrivate;
	@Resource(name = "wsPublic")
	private WSPublic wsPublic;
	

	@PayloadRoot(localPart = "createOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Owner createOwner(@RequestPayload CreateOwnerRequest request)
			throws Exception {
		// Utilisation d'un logger à la place
		// System.out.println("Debug createOwner : "+request.getLastName()+request.getFirstName()+request.getMail());

		return wsPublic.createOwner(request.getLastName(),
				request.getFirstName(), request.getMail());
	}

	@PayloadRoot(localPart = "getOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Owner getOwner(@RequestPayload GetOwnerRequest request)
			throws Exception {
		return wsPublic.getOwner(request.getMail());
	}

	@PayloadRoot(localPart = "signDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public SignDocumentResponse signDocument(@RequestPayload SignDocumentRequest request) {
		byte[] byteDoc = wsPublic.signDocument(request.getDoc(), request.getName(),
				request.getOwner(), request.getCompetence());
		SignDocumentResponse resp = new SignDocumentResponse();
		resp.setDoc(byteDoc);
		return resp;
	}

	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence) {
		// Cette méthode devrait-elle renvoyer un tableau vide plutôt que null ?
		return null;
	}

	@PayloadRoot(localPart = "createCertificateRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public void createCertificate(
			@RequestPayload CreateCertificateRequest request) {
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
	public GetDocumentInformationsResponse getDocumentInformations(
			@RequestPayload GetDocumentInformationsRequest request) {
		GetDocumentInformationsResponse getDocumentInformationsResponse = new GetDocumentInformationsResponse();
		System.out.println("requete idDoc:" + request.getIdDocument());
		Map<Owner, List<Competence>> lites = wsPrivate
				.getDocumentInformations(request.getIdDocument());
		lites.keySet();
		if (!lites.isEmpty()) {
			Map.Entry<Owner, List<Competence>> entry = lites.entrySet()
					.iterator().next();

			getDocumentInformationsResponse.setOwner(entry.getKey());

			getDocumentInformationsResponse.setCompetence(entry
					.getValue());
		}
		System.out.println("requete idDoc:5");
		return getDocumentInformationsResponse;

	}

	@PayloadRoot(localPart = "importDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public ImportDocumentResponse importDocument(
			@RequestPayload ImportDocumentRequest request) {
		System.out.println("request import document ownerId :"
				+ request.getOwner().getId());
		List<Document> dd = wsPrivate.importDocument(request.getOwner());
		ImportDocumentResponse importDocumentResponse = new ImportDocumentResponse();
		importDocumentResponse.setDocument(dd);
		return importDocumentResponse;
	}

	@PayloadRoot(localPart = "importCompetencesRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public List<Competence> importCompetences(@RequestPayload Document document) {
		return wsPrivate.importCompetences(document);
	}
							  
	@PayloadRoot(localPart = "getDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public GetDocumentResponse getDocument(
			@RequestPayload GetDocumentRequest request) {
		System.out.println("request import document ownerId :"
				+ request.getIdDocument());
		Document d = wsPrivate.getDocument(request.getIdDocument());
		GetDocumentResponse get = new GetDocumentResponse();
		get.setDocument(d);
		return get;
	}
}
