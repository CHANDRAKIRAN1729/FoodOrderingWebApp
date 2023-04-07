package com.foodapp.app.exception;

public class WrongCredentialsException extends RuntimeException {
	String message="Wrong Credentials";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
