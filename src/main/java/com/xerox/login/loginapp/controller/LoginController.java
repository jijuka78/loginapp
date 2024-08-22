package com.xerox.login.loginapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xerox.login.loginapp.model.User;


@RestController
public class LoginController {
	
	
	@RequestMapping ("/fetchUsers")
	public List<User> retrieveAllUsers(){
		return List.of(
				new User("jak056", "5765765"),
				new User("gyt657", "T*^T*7"),
				new User("hat067", "YFY$^%$^")
				);
	}
	
	
}
