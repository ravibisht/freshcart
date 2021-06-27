package com.freshcart.exception;
public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String  name) {
		super("Reason  Being Product Not Found That You Looking For With ID "+name);
	 }
}
