package fr.univartois.ili.sadoc.sadocweb.springsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class AccountBalanceServiceStub implements AccountService {

	private final AccountServiceImpl accountService;
	private Element order;

	@Autowired
	public AccountBalanceServiceStub(AccountServiceImpl accountService) {
		this.accountService = accountService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	@PayloadRoot(localPart = "accountBalanceRequest", namespace = "http://sadoc.com/ac/schemas")
	@ResponsePayload
	public Element getOrder(@RequestPayload Element order) {
		order.setAttribute("balance", "100.50");
		order.setAttribute("number", "123");
		order.setAttribute("time", "2009-05-30T09:30:10.5");
		return order;
	}

}