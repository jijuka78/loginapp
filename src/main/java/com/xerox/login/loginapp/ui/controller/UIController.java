package com.xerox.login.loginapp.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.ui.model.User;
import com.xerox.login.loginapp.ui.service.UIService;

@Controller
public class UIController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UIService uIService;
	
	@GetMapping("/getUser")
	public String getUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "userId";
	}
	
	@PostMapping("/getUser")
	public String getUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=false)  String userId,
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		if(ObjectUtils.isEmpty(userId)) {
			modelMap.put("message", "UserId should be provided");
			return "userId";
		}
		
		User user = uIService.retrieveUser(userId);
		if(user == null) {
			modelMap.put("message", "User with userId: " + userId + " does not exist");
		}else {
			modelMap.put("message", user + " is fetched");
		}
		return "menu";
	}
	
	@GetMapping("/createUser")
	public String addUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "user";
	}
	
	@PostMapping("/createUser")
	public String addUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=false)  String userId, 
			@RequestParam(value="password", required=false)  String password, 
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		boolean invalid = false;
		if(ObjectUtils.isEmpty(userId)) {
			modelMap.put("message", "UserId is required");
			invalid = true;
		}
		if(ObjectUtils.isEmpty(password)) {
			if(invalid) {
				modelMap.put("message", "Userid and password are required");
			} else {
				modelMap.put("message", "password is required");
				invalid = true;
			}
		}
		if(invalid) {
			return "user";
		}
		
		modelMap.put("message", "User with Userid:" + userId  + "is created");
		try {
			uIService.createUser(new UserBean(userId, password));
		}catch (HttpClientErrorException ex) {
			modelMap.put("message", ex.getMessage());
			return "user";
		}
		return "menu";
	}
	
	@GetMapping("/menu")
	public String showMenu(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "menu";
	}
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "user";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId, 
			@RequestParam(value="password", required=true)  String password, 
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		boolean invalid = false;
		if(ObjectUtils.isEmpty(userId)) {
			modelMap.put("message", "UserId is required");
			invalid = true;
		}
		if(ObjectUtils.isEmpty(password)) {
			if(invalid) {
				modelMap.put("message", "Userid and password are required");
			} else {
				modelMap.put("message", "password is required");
				invalid = true;
			}
		}
		if(invalid) {
			return "user";
		}
		
		try {
			uIService.updateUser(new UserBean(userId, password), userId);
		}catch (HttpClientErrorException ex) {
			modelMap.put("message", ex.getMessage());
			return "user";
		}
		modelMap.put("message", "User with Userid:" + userId  + "is updated");
		return "menu";
	}
	
	@GetMapping("/removeUser")
	public String removeUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "userId";
	}
	
	@PostMapping("/removeUser")
	public String removeUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId,
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		if(ObjectUtils.isEmpty(userId)) {
			modelMap.put("message", "UserId is required");
			return "userId";
		}
		modelMap.put("message", "User with Userid:" + userId  + "is removed");
		
		try {
			uIService.deleteUser(userId);
		}catch (HttpClientErrorException ex) {
			
			modelMap.put("message", ex.getMessage());
			return "userId";
		}
		return "menu";
	}
	
	
	@GetMapping("/listAllUsers")
	public String listAllUsers(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		
		List<User> users = uIService.retrieveAllUsers();
		modelMap.put("users", users);
		return "users";
	}
	
	@PostMapping("/listAllUsers")
	public String goBacktoMenu(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (ObjectUtils.isEmpty(name))? "you": name.trim().toUpperCase());
		return "menu";
	}
}
