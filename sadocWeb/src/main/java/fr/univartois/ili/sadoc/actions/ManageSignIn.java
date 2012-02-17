package fr.univartois.ili.sadoc.actions;

import java.io.IOException;
import fr.univartois.ili.sadoc.entities.Owner;
import java.util.Date;

public class ManageSignIn {
	
	private String firsname;
	private String name;
	private String mail;
	private String password;
	private String password2;
	
	
	public String getFirsname() {
		return firsname;
	}
	public void setFirsname(String firsname) {
		this.firsname = firsname;
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
	
	public String signIn() {
		System.out.println("Dans la méthode enregistrer...");
		Owner personne = new Owner();
		if(this.name.equals("")) {
			return "input";
		}

		return "success";
      }
	
}