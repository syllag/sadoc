package fr.univartois.ili.sadoc.sadocweb.springsample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeader;
import org.w3c.dom.Element;

@Endpoint                                                                                
public class AccountBalanceServiceStub {

  private final AccountService accountService;

  @Autowired                                                                             
  public AccountBalanceServiceStub(AccountService accountService) {
      this.accountService = accountService;
  }

  @PayloadRoot(localPart = "order", namespace = "http://sadoc.com/ac/schemas")                        
  public void order(@RequestPayload Element orderElement) {                              
    accountService.createOrder();
  }

  @PayloadRoot(localPart = "accountBalanceRequest", namespace = "http://sadoc.com/ac/schemas")                 
  @ResponsePayload
  public String getOrder(@RequestPayload OrderRequest orderRequest, SoapHeader header) {
//    return accountService.getOrder(Integer.toString(orderRequest.getId()));
	  return "123";
  }
  
}