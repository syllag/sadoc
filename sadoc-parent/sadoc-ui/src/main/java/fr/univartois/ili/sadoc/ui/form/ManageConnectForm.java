package fr.univartois.ili.sadoc.ui.form;

public class ManageConnectForm {

	private String email;
	private String password;

	public ManageConnectForm() {
		super();
	}

	public ManageConnectForm(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
