package fr.univartois.ili.sadoc.sadocweb.springsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountBalanceServiceStub implements AccountService {

	private final AccountServiceImpl accountService;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	@Autowired
	public AccountBalanceServiceStub(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	@PayloadRoot(localPart = "accountBalanceRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public OrderRequest getOrder(@RequestPayload OrderRequest order) {
		order.setNumber(123);
		order.setBalance(100.50);
		order.setTime("2009-05-30T09:30:10.5");
		return order;
	}

}