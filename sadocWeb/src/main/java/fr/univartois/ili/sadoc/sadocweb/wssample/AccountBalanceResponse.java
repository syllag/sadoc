package fr.univartois.ili.sadoc.sadocweb.wssample;

public class AccountBalanceResponse {

	private Order orderRequest;

	public AccountBalanceResponse() {
		Order order = new Order();
		order.setBalance(2.50);
		order.setNumber(123);
		order.setTime("2009-05-30T09:30:10.5");
		this.setOrderRequest(order);
	}

	public Order getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(Order order) {
		this.orderRequest = order;
	}

}
