package fr.univartois.ili.sadoc.sadocweb.springsample;

public class AccountServiceImpl implements AccountService {

	public OrderRequest createOrder() {
		return new OrderRequest();
	}
	
	public String getOrder(String order) {
		return order;
		
	}
}
