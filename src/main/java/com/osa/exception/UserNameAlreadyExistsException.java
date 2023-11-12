package com.osa.exception;

public class UserNameAlreadyExistsException  extends RuntimeException{
	public UserNameAlreadyExistsException(String msg) {
		super(msg);
	}

}
