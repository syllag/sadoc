package fr.univartois.ili.sadoc.sadocweb.wssample;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

public interface AccountService {
	
	AccountBalanceResponse getOrder(@RequestPayload AccountBalanceRequest request);
}
