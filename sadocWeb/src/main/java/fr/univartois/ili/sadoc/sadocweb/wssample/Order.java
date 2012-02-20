package fr.univartois.ili.sadoc.sadocweb.wssample;

public class Order {
	
	private int number;
	private double balance;
	private String time;
	
	public Order() {
		this.number = 999;
		this.balance = 45454545;
		this.time = "order time from constructor";
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
