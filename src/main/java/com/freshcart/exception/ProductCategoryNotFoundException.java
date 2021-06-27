package com.freshcart.exception;



public class ProductCategoryNotFoundException extends Exception {

	public ProductCategoryNotFoundException(int id) {
		super("Reason  Being  Project Category Not Found With Id : "+id);
		
	}

	
	
}
