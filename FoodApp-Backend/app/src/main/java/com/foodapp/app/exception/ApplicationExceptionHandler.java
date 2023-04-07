package com.foodapp.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foodapp.app.util.ResponseStructure;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
@ExceptionHandler(IdNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> idNotFoundException(IdNotFoundException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.NOT_FOUND.value());
    structure.setData("No such ID in Database");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
}

@ExceptionHandler(EmailNotSentException.class)
public ResponseEntity<ResponseStructure<String>> emailNotSentException(EmailNotSentException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.NOT_FOUND.value());
    structure.setData("Email Could Not Be Sent");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
}

@ExceptionHandler(BranchManagerException.class)
public ResponseEntity<ResponseStructure<String>> branchMangerLinkedException(BranchManagerException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.CONFLICT.value());
    structure.setData("Menu Cannot be Linked");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CONFLICT);
}

@ExceptionHandler(WrongCredentialsException.class)
public ResponseEntity<ResponseStructure<String>> wrongCredentialsException(WrongCredentialsException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.UNAUTHORIZED.value());
    structure.setData("Enter the Correct Credentials");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.UNAUTHORIZED);
}

@ExceptionHandler(NullPointerException.class)
public ResponseEntity<ResponseStructure<String>> nullPointerException(NullPointerException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
    structure.setData("Enter Value Null Not Accepted");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
}

@ExceptionHandler(UniqueException.class)
public ResponseEntity<ResponseStructure<String>> uniqueException(UniqueException exception){
    ResponseStructure<String> structure=new ResponseStructure<>();
    structure.setMessage(exception.getMessage());
    structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
    structure.setData("Value Already Exists! Unique Value Needed");
    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
}


}
