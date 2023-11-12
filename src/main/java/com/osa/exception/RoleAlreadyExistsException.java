package com.osa.exception;

public class RoleAlreadyExistsException extends RuntimeException{
	public RoleAlreadyExistsException(String msg) {
		super(msg);
	}

}
