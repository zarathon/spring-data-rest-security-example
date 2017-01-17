package com.greenmile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String message = "Member not exist.";
	
	public MemberNotFoundException() {
		super(MemberNotFoundException.message);
	}
	
	public MemberNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MemberNotFoundException(String message){
		super(message);
	}
	
	public MemberNotFoundException(Throwable cause){
		super(cause);
	}
	
}
