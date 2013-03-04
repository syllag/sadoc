package fr.univartois.ili.sadoc.ui.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

	public static String encrypt(String password) throws NoSuchAlgorithmException {
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] p = messageDigest.digest(password.getBytes());
		StringBuilder hashString = new StringBuilder();
		for (int i = 0; i < p.length; ++i) {
			String hex;
			hex = Integer.toHexString(p[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}
}
