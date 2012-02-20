package fr.univartois.ili.sadoc.sadocweb.wssample;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

public class AccountServiceImpl implements AccountService {

	public AccountBalanceResponse response = new AccountBalanceResponse();

	public AccountBalanceResponse getOrder(@RequestPayload AccountBalanceRequest request) {
		return response;

	}
}
