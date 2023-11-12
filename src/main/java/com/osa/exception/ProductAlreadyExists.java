package com.osa.exception;

public class ProductAlreadyExists extends RuntimeException{
	public ProductAlreadyExists(String msg){
		super(msg);
		
	}

}
