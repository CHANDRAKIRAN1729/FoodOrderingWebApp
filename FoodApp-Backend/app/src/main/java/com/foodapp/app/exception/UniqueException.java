package com.foodapp.app.exception;

public class UniqueException extends RuntimeException {
	String message="Unique Value Constraint";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}