package fr.univartois.ili.sadoc.sadocweb.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.univartois.ili.sadoc.entities.Certificate;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

@Endpoint
public class WSStub implements WSPrivate, WSPublic {

	private final WSPrivate wsPrivate;
	private final WSPublic wsPublic;

	@Autowired
	public WSStub(WSPrivateImpl wsPrivate, WSPublicImpl wsPublic) {
		this.wsPrivate = wsPrivate;
		this.wsPublic = wsPublic;
	}

	@PayloadRoot(localPart = "createOwnerRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Owner createOwner(@RequestPayload String nom,
			@RequestPayload String prenom, @RequestPayload String mail)
			throws Exception {
		return wsPublic.createOwner(nom, prenom, mail);
	}

	@PayloadRoot(localPart = "signDocumentRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public byte[] signDocument(@RequestPayload byte[] doc,
			@RequestPayload String name, @RequestPayload Owner owner,
			@RequestPayload Competence[] competence) {
		return wsPublic.signDocument(doc, name, owner, competence);
	}

	public byte[] signDocument(byte[] doc, String name, Certificate certificat,
			Competence[] competence) {
		return null;
	}

	@PayloadRoot(localPart = "createCertificateRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public void createCertificate(@RequestPayload Owner owner) {
		wsPublic.createCertificate(owner);
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

	public WSPrivate getWsPrivate() {
		return wsPrivate;
	}

	public WSPublic getWsPublic() {
		return wsPublic;
	}

}
