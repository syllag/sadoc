package fr.univartois.ili.sadoc.sadocweb.wssample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountBalanceEndpoint implements AccountService {

	// The namespace of both request and response as declared in the XSD file
	 public static final String NAMESPACE_URI = "http://sadoc.com/ac/schemas";
	 
	private final AccountServiceImpl accountService;

	@Autowired
	public AccountBalanceEndpoint(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	@PayloadRoot(localPart = "accountBalanceRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public AccountBalanceResponse getOrder(@RequestPayload AccountBalanceRequest request) {
		User usr = request.getUser();
		AccountBalanceResponse response = new AccountBalanceResponse();
		if(usr.getNumber() == 123) {
			response.getOrderRequest().setTime("user 123 detected in the request !");
		} else {
			Order order = new Order();
			response.setOrderRequest(order);
		}
		
		return response;
	}

}