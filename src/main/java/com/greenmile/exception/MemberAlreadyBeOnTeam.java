package com.greenmile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MemberAlreadyBeOnTeam extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "This member is already on team.";
	
	public MemberAlreadyBeOnTeam() {
		super(MemberAlreadyBeOnTeam.message);
	}
	
	public MemberAlreadyBeOnTeam(String message, Throwable cause){
		super(message, cause);
	}
	
	public MemberAlreadyBeOnTeam(String message){
		super(message);
	}
	
	public MemberAlreadyBeOnTeam(Throwable cause){
		super(cause);
	}

}
