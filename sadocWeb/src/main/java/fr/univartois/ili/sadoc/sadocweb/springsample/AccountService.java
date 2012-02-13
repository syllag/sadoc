package fr.univartois.ili.sadoc.sadocweb.springsample;

public interface AccountService {

	public OrderRequest createOrder();
	
	public String getOrder(String order);
}
