package fr.univartois.ili.sadoc.ui.form;

public class ManageSignInForm {

	private String firstname;
	private String name;
	private String mail;
	private String password;
	private String password2;

	public ManageSignInForm() {
	}
	
	public ManageSignInForm(String firstname, String name, String mail,
			String password, String password2) {
		super();
		this.firstname = firstname;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.password2 = password2;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
