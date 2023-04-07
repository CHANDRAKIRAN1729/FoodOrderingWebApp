package com.foodapp.app.exception;

public class NullPointerException extends RuntimeException {
	String message="Null Value Not Accepted";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}