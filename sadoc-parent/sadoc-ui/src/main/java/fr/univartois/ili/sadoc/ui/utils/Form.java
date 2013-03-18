package fr.univartois.ili.sadoc.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form {
	public static final boolean isValidEmailAddress(String mail) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(mail.toUpperCase());
		return m.matches();
	}
	
	public static String normalizeFirstName(String firstName) {
		String res="";
		if(firstName != null && firstName.length() > 0) {
			res+=firstName.toUpperCase().charAt(0) ;
			res+=firstName.substring(1) ;
		}
		return res;
	}
	
	public static final String normalizeLastName(String lastName) {
		if(lastName != null && lastName.length() > 0) {
			return lastName.toUpperCase() ;
		}
		return lastName ;
	}
	
	public static final boolean isCorrectZipCode(String zipCode) {
		return zipCode.matches("[0-9]{5}") ;
	}
	
	public static final boolean isCorrectPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("[0-9]{10}") ;
	}
	
	public static final String normalizeAddress(String address) {
		if(address != null && address.length() > 0) {
			return address.toUpperCase() ;
		}
		return address ;
	}
	
	public static final String normalizeTown(String town) {
		if(town != null && town.length() > 0) {
			return town.toUpperCase() ;
		}
		return town ;
	}
}
