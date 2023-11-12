package com.osa.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {
	
	private String errorMessage;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy  hh:mm:ss")
	private LocalDateTime timestamp;
	private Integer errorCode;
	
	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExceptionResponse(String errorMessage, LocalDateTime timestamp, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	


}