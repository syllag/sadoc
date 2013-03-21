package fr.univartois.ili.sadoc.ws.spring;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.univartois.ili.sadoc.metier.ws.vo.Certificate;
import fr.univartois.ili.sadoc.metier.ws.vo.Competence;
import fr.univartois.ili.sadoc.metier.ws.vo.Document;
import fr.univartois.ili.sadoc.metier.ws.vo.Owner;

//TODO Replace DAO
@Endpoint
public class WSStub {

	@Resource(name = "wsPrivate")
	private WSPrivate wsPrivate;

	@Resource(name = "wsPublic")
	private WSPublic wsPublic;

	@PayloadRoot(localPart = "createOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public CreateOwnerResponse createOwner(
			@RequestPayload CreateOwnerRequest request) throws Exception {
		CreateOwnerResponse resp = new CreateOwnerResponse();
		Owner o = wsPublic.createOwner(request.getMail_initial());
		resp.setOwner(o);
		return resp;
	}

	@PayloadRoot(localPart = "getOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Owner getOwner(@RequestPayload GetOwnerRequest request)
			throws Exception {
		return wsPublic.getOwner(request.getMail());
	}

	@PayloadRoot(localPart = "signDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public SignDocumentResponse signDocument(
			@RequestPayload SignDocumentRequest request) {
		byte[] byteDoc = wsPublic.signDocument(request.getDoc(),
				request.getName(), request.getOwner(), request.getAcquisition());
		SignDocumentResponse resp = new SignDocumentResponse();
		resp.setDoc(byteDoc);
		return resp;
	}

	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence) {
		// TODO ???
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
		// TODO ???getAllDocumentByOwner
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

		Map<Owner, List<Competence>> lites = wsPrivate
				.getDocumentInformations(request.getIdDocument());
		lites.keySet();
		if (!lites.isEmpty()) {
			Map.Entry<Owner, List<Competence>> entry = lites.entrySet()
					.iterator().next();

			getDocumentInformationsResponse.setOwner(entry.getKey());

			getDocumentInformationsResponse.setCompetence(entry.getValue());
		}

		return getDocumentInformationsResponse;

	}

	
	

	@PayloadRoot(localPart = "getDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public GetDocumentResponse getDocument(
			@RequestPayload GetDocumentRequest request) {

		Document d = wsPrivate.getDocument(request.getIdDocument());
		GetDocumentResponse get = new GetDocumentResponse();
		get.setDocument(d);
		return get;
	}

	@PayloadRoot(localPart = "owner", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public List<Document> getAllDocumentByOwner(@RequestPayload Owner user) {
		return wsPublic.getAllDocumentByOwner(user);
	}

}
