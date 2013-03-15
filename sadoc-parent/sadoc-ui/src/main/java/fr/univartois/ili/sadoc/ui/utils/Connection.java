package fr.univartois.ili.sadoc.ui.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import fr.univartois.ili.sadoc.metier.ui.vo.Owner;

public class Connection {

	private static final String USER = "connectedOwner";
	private static MessageDigest messageDigest = null;
	
	public static final void setUser(Map<String, Object> session, Owner owner) {
		session.put(USER,owner);
	}
	
	public static final Owner getUser(Map<String, Object> session) {
		return (Owner) session.get(USER);
	}
	
	public static String encryptPassword(String password) throws NoSuchAlgorithmException {
		StringBuilder hashString = new StringBuilder();
		String hex;
		
		messageDigest = MessageDigest.getInstance("MD5");
		byte[] p = messageDigest.digest(password.getBytes());
		
		for (int i = 0; i < p.length; ++i) {
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
