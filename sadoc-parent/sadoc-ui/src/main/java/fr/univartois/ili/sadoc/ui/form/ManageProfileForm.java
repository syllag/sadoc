package fr.univartois.ili.sadoc.ui.form;

public class ManageProfileForm {

	private String password;
	private String password2;
	private String address;
	private String zipCode;
	private String town;
	private String phone;
	private String mail;

	/************************************************/

	public ManageProfileForm() {

	}

	public ManageProfileForm(String firstName, String lastName,
			String password, String password2, String adress, String zipCode,
			String town, String phone, String mail) {
		this.password = password;
		this.password2 = password2;
		this.address = adress;
		this.zipCode = zipCode;
		this.town = town;
		this.phone = phone;
		this.mail = mail;
	}

	/************************************************/

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
