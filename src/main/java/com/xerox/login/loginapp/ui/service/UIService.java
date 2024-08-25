package com.xerox.login.loginapp.ui.service;

import java.util.List;

import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.ui.model.User;


public interface UIService {
	
	public List<User> retrieveAllUsers();
	
	public User retrieveUser(String userId);
	
	public User createUser(UserBean user);
	
	public User updateUser(UserBean user, String userId);
	
	public void deleteUser(String userId);

}
