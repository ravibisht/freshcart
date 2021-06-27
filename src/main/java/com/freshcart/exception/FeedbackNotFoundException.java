package com.freshcart.exception;

public class FeedbackNotFoundException extends Exception {

	public FeedbackNotFoundException(int id) {
		super("Reason  Being  No FeedBack Found With Id "+id);
		
	}	
}
