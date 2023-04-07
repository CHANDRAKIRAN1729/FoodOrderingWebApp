package com.foodapp.app.exception;

public class BranchManagerException extends RuntimeException {
	String message="Branch Manager Already Linked to Another Menu";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}