package com.greenmile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String message = "Team not exist.";
	
	public TeamNotFoundException() {
		super(TeamNotFoundException.message);
	}
	
	public TeamNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TeamNotFoundException(String message){
		super(message);
	}
	
	public TeamNotFoundException(Throwable cause){
		super(cause);
	}
	
}
