package fr.univartois.ili.sadoc.client.webservice;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerResponse;
import fr.univartois.ili.sadoc.client.webservice.tools.Document;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentByOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentInformationsRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentInformationsResponse;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.GetDocumentResponse;
import fr.univartois.ili.sadoc.client.webservice.tools.GetOwnerRequest;
import fr.univartois.ili.sadoc.client.webservice.tools.Owner;

/**
 * manage client web service
 * 
 * @author habib
 * 
 */
public class ClientWebServiceImpl implements IClientWebService {

	private ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContextMetierUI.xml");
	private WebServiceTemplate webServiceTemplate = (WebServiceTemplate) applicationContext
			.getBean("webServiceTemplate");
	
	public ClassPathXmlApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(
			ClassPathXmlApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public WebServiceTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(
			long id) {

		fr.univartois.ili.sadoc.client.webservice.tools.Document response = null;
		try {

			GetDocumentRequest getDocumentRequest = new GetDocumentRequest();
			getDocumentRequest.setIdDocument(id);

			GetDocumentResponse responses = (GetDocumentResponse) webServiceTemplate
					.marshalSendAndReceive(getDocumentRequest);

			response = responses.getDocument();
		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return response;
	}

	public Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Acquisition>> getAcquisitions(
			long idDoc) {

		Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Acquisition>> responses = null;
		try {
			GetDocumentInformationsRequest getDocumentInformationsRequest = new GetDocumentInformationsRequest();
			getDocumentInformationsRequest.setIdDocument(idDoc);

			GetDocumentInformationsResponse response = (GetDocumentInformationsResponse) webServiceTemplate
					.marshalSendAndReceive(getDocumentInformationsRequest);
			responses = new HashedMap();
			responses.put(response.getOwner(), response.getAcquisition());

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return responses;

	}

	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(
			String mail) {
		CreateOwnerResponse response = null;
		try {

			CreateOwnerRequest createOwnerRequest = new CreateOwnerRequest();
			createOwnerRequest.setMailInitial(mail);
			
			response = (CreateOwnerResponse) webServiceTemplate
					.marshalSendAndReceive(createOwnerRequest);

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return response.getOwner();
	}

	public Owner getOwner(String mail) {
		fr.univartois.ili.sadoc.client.webservice.tools.Owner response = null;
		try {

			GetOwnerRequest getOwnerRequest = new GetOwnerRequest();

			getOwnerRequest.setMail(mail);
			response = (fr.univartois.ili.sadoc.client.webservice.tools.Owner) webServiceTemplate
					.marshalSendAndReceive(getOwnerRequest);

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return response;
	}

	public List<Document> getAllDocument(long idOwner) {
		// TODO a implementer
		return null;
	}

	public List<Document> getAllDocument(
			fr.univartois.ili.sadoc.metier.ui.vo.Owner owner) {
		List<fr.univartois.ili.sadoc.client.webservice.tools.Document> responses = null;
		try {
			GetDocumentByOwnerRequest getDocumentByOwnerRequest = new GetDocumentByOwnerRequest();
			getDocumentByOwnerRequest.setIdOwnerWs(owner.getId());

			List<fr.univartois.ili.sadoc.client.webservice.tools.Document> responsess = (List<fr.univartois.ili.sadoc.client.webservice.tools.Document>) webServiceTemplate
					.marshalSendAndReceive(getDocumentByOwnerRequest);
			responses = (List<Document>) ((GetDocumentResponse) responsess).getDocument();

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return responses;

	}

}
