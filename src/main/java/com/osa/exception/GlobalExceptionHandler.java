package com.osa.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
        objectBody.put("errorCode", status.value());
        List<String> exceptions = ex.getBindingResult().getFieldErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList());
        objectBody.put("errorMessages", exceptions);
        return new ResponseEntity<Object>(objectBody, status);
	}
	
	@ExceptionHandler(RoleAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse>handler(RoleAlreadyExistsException ex){
		ExceptionResponse exception=new ExceptionResponse(ex.getMessage(),LocalDateTime.now(),HttpStatus.OK.value());
		ResponseEntity<ExceptionResponse>response =new ResponseEntity<ExceptionResponse>(exception,HttpStatus.OK);
		return response;
	}
	@ExceptionHandler(UserNameAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse>handler(UserNameAlreadyExistsException ex){
		ExceptionResponse exception=new ExceptionResponse(ex.getMessage(),LocalDateTime.now(),HttpStatus.OK.value());
		ResponseEntity<ExceptionResponse>response =new ResponseEntity<ExceptionResponse>(exception,HttpStatus.OK);
		return response;
	}
	@ExceptionHandler(ProductAlreadyExists.class)
	public ResponseEntity<ExceptionResponse>handler(ProductAlreadyExists ex){
		ExceptionResponse exception=new ExceptionResponse(ex.getMessage(),LocalDateTime.now(),HttpStatus.FORBIDDEN.value());
		ResponseEntity<ExceptionResponse>response =new ResponseEntity<ExceptionResponse>(exception,HttpStatus.FORBIDDEN);
		return response;
	}
	@ExceptionHandler(ProductIdNotFound.class)
	public ResponseEntity<ExceptionResponse>handler(ProductIdNotFound ex){
		ExceptionResponse exception=new ExceptionResponse(ex.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse>response =new ResponseEntity<ExceptionResponse>(exception,HttpStatus.NOT_FOUND);
		return response;
	}
	
	

}