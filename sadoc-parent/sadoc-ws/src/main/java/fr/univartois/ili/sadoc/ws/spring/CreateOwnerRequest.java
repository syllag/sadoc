package fr.univartois.ili.sadoc.ws.spring;


public class CreateOwnerRequest {
	
	private String mail_initial;
	
	public CreateOwnerRequest() {
	}
	
	public CreateOwnerRequest(String mail_initial) {	
		this.mail_initial = mail_initial;
	}

	/**
	 * @return the mail_initial
	 */
	public String getMail_initial() {
		return mail_initial;
	}

	/**
	 * @param mail_initial the mail_initial to set
	 */
	public void setMail_initial(String mail_initial) {
		this.mail_initial = mail_initial;
	}
		
	
	
}
