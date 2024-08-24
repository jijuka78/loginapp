package com.xerox.login.loginapp.exception;

import com.xerox.login.loginapp.model.UserBean;

import jakarta.persistence.EntityExistsException;

public class UserAlreadyExistsException extends EntityExistsException{
	
	
	public UserAlreadyExistsException(UserBean user) {
		super(buildMessage(user.getUserId()));
	}
	
	private static String buildMessage(String userId) {
		StringBuilder message = new StringBuilder();
		message.append("UserId: ").append(userId).append(" already exists");
		return message.toString();
	}

}
