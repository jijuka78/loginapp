package com.xerox.login.loginapp.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException{
	
	
	public UserNotFoundException(String userId) {
		super(buildMessage(userId));
	}
	
	private static String buildMessage(String userId) {
		StringBuilder message = new StringBuilder();
		message.append("UserId: ").append(userId).append(" not found");
		return message.toString();
	}

}
