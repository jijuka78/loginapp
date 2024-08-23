package com.xerox.login.loginapp.model;

public class UserBean {

	
	private String userId;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserBean(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	public UserBean() {
		super();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}
	
	
}
