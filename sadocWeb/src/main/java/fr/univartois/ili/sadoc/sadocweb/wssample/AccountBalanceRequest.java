package fr.univartois.ili.sadoc.sadocweb.wssample;

public class AccountBalanceRequest {
	
	private User user;
	
	public AccountBalanceRequest() {
		User usr = new User();
		usr.setName("Lahoucine");
		usr.setNumber(123);
		this.user = usr;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
}
