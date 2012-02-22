package fr.univartois.ili.sadoc.Form;

/**
 * @author Kevin Pogorzelski <kevin.pogorzelski at gmail.com>
 * 
 */
public class ManageProfileForm {
	private String firstName;
	private String lastName;
	private String password;
	private String password2;
	private String adress;
	private String zipCode;
	private String town;
	private String phone;
	private String mail;
	
	/************************************************/
	
	public ManageProfileForm (String firstName, String lastName, String password, String password2,
			String adress, String zipCode, String town, String phone, String mail) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.password=password;
		this.password2=password2;
		this.adress=adress;
		this.zipCode=zipCode;
		this.town=town;
		this.phone=phone;
		this.setMail(mail);
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress=adress;
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
