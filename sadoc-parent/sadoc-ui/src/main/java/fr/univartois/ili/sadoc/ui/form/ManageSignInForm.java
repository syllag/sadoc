package fr.univartois.ili.sadoc.ui.form;

public class ManageSignInForm {

	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	private String password2;

	/************************************************/

	public ManageSignInForm() {
	}

	public ManageSignInForm(String firstName, String lastName, String mail,
			String password, String password2) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.password2 = password2;
	}

	/************************************************/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
