package com.vgnshiyer.blog.api.exceptions;

public class InvalidCredentialException extends RuntimeException {

	public InvalidCredentialException() {
		super("Username or Password is invalid!");
	}
	
}
