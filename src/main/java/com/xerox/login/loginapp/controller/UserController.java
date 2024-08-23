package com.xerox.login.loginapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xerox.login.loginapp.ui.model.User;


@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping ("/fetchUsers")
	public List<User> retrieveAllUsers(){
		//TODO retrieveUsers
		//TODO Exception handling
		return List.of(
				new User("jak056", "5765765"),
				new User("gyt657", "T*^T*7"),
				new User("hat067", "YFY$^%$^")
				);
	}
	
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable (value="userId", required=true) String userId) {
		User user = new User(userId, "tiytiy");
		//TODO retrieveUseer 
				//TODO Exception handling
		return user;
	}
	
	@PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
	public User createUser(@RequestBody User user) throws Exception {

	   //TODO addUseer 
		//TODO Exception handling
	  return user;
	}
	
	@PutMapping(value = "/user/{userId}", consumes = "application/json", produces = "application/json")
	public User updateUser(@PathVariable (value="userId", required=true) String userId,
			@RequestBody User user) throws Exception {
		user.setUserId(userId);
	   //TODO updateUseer 
		//TODO Exception handling
	  return user;
	}
	
	@DeleteMapping("/user/{userId}")
	public User removeUser(@PathVariable (value="userId", required=true) String userId) {
		User user = new User(userId, "tiytiy");
		//TODO removeUseer 
				//TODO Exception handling
		return user;
	}
	
	
}
