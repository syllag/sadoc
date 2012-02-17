package fr.univartois.ili.sadoc.sadocweb.springsample;

public class OrderRequest {
	
	private int number;
	private double balance;
	private String time;
	
	public OrderRequest() {
		this.number = 123;
		this.balance = 100.50;
		this.time = "2009-05-30T09:30:10.5";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
