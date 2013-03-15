package fr.univartois.ili.sadoc.ui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form {
	public static boolean isValidEmailAddress(String mail) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(mail.toUpperCase());
		return m.matches();
	}
	
	public static String normalizeFirstName(String firstName) {
		String res="";
		if(firstName != null && firstName.length() > 0) {
			res+=firstName.charAt(0);
			res=res.toUpperCase();
			for(int i=1;i<firstName.length();i++) {
				res+=firstName.charAt(i);
			}
		}
		return res;
	}
	
	public static String normalizeLastName(String lastName) {
		String res="";
		if(lastName != null && lastName.length() > 0) {
			res+=lastName.charAt(0);
			res=res.toUpperCase();
			for(int i=1;i<lastName.length();i++) {
				res+=lastName.charAt(i);
			}
		}
		return res;
	}
}
