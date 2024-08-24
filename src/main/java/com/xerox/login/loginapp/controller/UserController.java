package com.xerox.login.loginapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xerox.login.loginapp.exception.UserAlreadyExistsException;
import com.xerox.login.loginapp.exception.UserNotFoundException;
import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.repository.UserRepository;



@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping ("/users")
	public List<UserBean> retrieveAllUsers(){
		List<UserBean> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/user/{userId}")
	public UserBean getUser(@PathVariable (value="userId", required=true) String userId) {
		UserBean userExisting = userRepository.findByUserId(userId);
		if(userExisting == null) {
			throw new UserNotFoundException(userId);
		}
		return userExisting;
	}
	
	@PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
	public UserBean createUser(@RequestBody UserBean user) throws Exception {
		UserBean userExisting = getUser(user.getUserId());
		UserBean userSaved = null;
		if(userExisting == null) {
			userSaved = userRepository.save(user);
		}else {
			throw new UserAlreadyExistsException(user);
		}
	  return userSaved;
	}
	
	@PutMapping(value = "/user/{userId}", consumes = "application/json", produces = "application/json")
	public UserBean updateUser(@PathVariable (value="userId", required=true) String userId,
			@RequestBody UserBean user) throws Exception {
		UserBean userExisting = getUser(userId);
		UserBean userSaved = null;
		if(userExisting == null) {
			throw new UserNotFoundException(userId);
		} else {
			user.setUserId(userId);
			user.setId(userExisting.getId());
			userSaved = userRepository.save(user);
			
		}
	  return userSaved;
	}
	
	@DeleteMapping("/user/{userId}")
	public UserBean removeUser(@PathVariable (value="userId", required=true) String userId) {
		UserBean userExisting = getUser(userId);
		if(userExisting == null) {
			throw new UserNotFoundException(userId);
		}else {
			userRepository.delete(userExisting);
		}
		return userExisting;
	}
	
	
}
