package com.xerox.login.loginapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xerox.login.loginapp.ui.model.User;


@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping ("/fetchUsers")
	public List<User> retrieveAllUsers(){
		return List.of(
				new User("jak056", "5765765"),
				new User("gyt657", "T*^T*7"),
				new User("hat067", "YFY$^%$^")
				);
	}
	
	
	
	
	
}
