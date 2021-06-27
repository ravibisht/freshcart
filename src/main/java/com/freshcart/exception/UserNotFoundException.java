package com.freshcart.exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String username) {
		
		super("Reason  Being User Not Found With Username  "+username);
		
	}

	public UserNotFoundException(int id) {
		super("Reason  Being User Not Found With Id "+id);
	}
}
