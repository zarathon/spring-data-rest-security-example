package com.greenmile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MemberAlreadyBeOnTeamException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "This member is already on team.";
	
	public MemberAlreadyBeOnTeamException() {
		super(MemberAlreadyBeOnTeamException.message);
	}
	
	public MemberAlreadyBeOnTeamException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MemberAlreadyBeOnTeamException(String message){
		super(message);
	}
	
	public MemberAlreadyBeOnTeamException(Throwable cause){
		super(cause);
	}

}
