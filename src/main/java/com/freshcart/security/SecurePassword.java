package com.freshcart.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SecurePassword {

	public String generateSecurePassword(String inputPassword) {
		
	String securePassword="";
		try {
			
			MessageDigest md=MessageDigest.getInstance("SHA-1");
		    md.update(inputPassword.getBytes());
		    byte []digestedByte=md.digest();
		    securePassword=DatatypeConverter.printHexBinary(digestedByte);
	      } 
		
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		  }
		
		
		return securePassword;
	}
	
	
	public boolean verifyUserPassword(String providedPassword,String securedPassword) {
		
		boolean result=false;
		String newSecuredPassword=generateSecurePassword(providedPassword);
		result=newSecuredPassword.equalsIgnoreCase(securedPassword);
		
		return result;
	}
	
}
