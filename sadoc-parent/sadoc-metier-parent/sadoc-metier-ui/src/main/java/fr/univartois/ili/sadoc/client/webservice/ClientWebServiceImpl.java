package fr.univartois.ili.sadoc.client.webservice;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import fr.univartois.ili.sadoc.client.webservice.tools.CreateOwnerRequest;
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

	public fr.univartois.ili.sadoc.client.webservice.tools.Document getDocument(
			long id) {

		fr.univartois.ili.sadoc.client.webservice.tools.Document response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {

			GetDocumentRequest getDocumentRequest = new GetDocumentRequest();
			getDocumentRequest.setIdDocument(BigInteger.valueOf(id));

			GetDocumentResponse responses = (GetDocumentResponse) webServiceTemplate
					.marshalSendAndReceive(getDocumentRequest);

			response = responses.getDocument();
		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return response;
	}

	public Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> getCompetences(
			long idDoc) {

		Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> responses = null;

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {
			GetDocumentInformationsRequest getDocumentInformationsRequest = new GetDocumentInformationsRequest();
			getDocumentInformationsRequest.setIdDocument(BigInteger
					.valueOf(idDoc));

			GetDocumentInformationsResponse response = (GetDocumentInformationsResponse) webServiceTemplate
					.marshalSendAndReceive(getDocumentInformationsRequest);
			responses = new HashedMap();
			responses.put(response.getOwner(), response.getCompetence());

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return responses;

	}

	public fr.univartois.ili.sadoc.client.webservice.tools.Owner createOwner(
			String firstName, String lastName, String mail) {
		fr.univartois.ili.sadoc.client.webservice.tools.Owner response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
		try {

			CreateOwnerRequest createOwnerRequest = new CreateOwnerRequest();
			createOwnerRequest.setMail_initial(mail);
			response = (fr.univartois.ili.sadoc.client.webservice.tools.Owner) webServiceTemplate
					.marshalSendAndReceive(createOwnerRequest);

		} catch (Exception sfce) {

			sfce.printStackTrace();
		}

		return response;
	}

	public Owner getOwner(String mail) {
		fr.univartois.ili.sadoc.client.webservice.tools.Owner response = null;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
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
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"service-client.xml");

		WebServiceTemplate webServiceTemplate = applicationContext
				.getBean(WebServiceTemplate.class);
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
