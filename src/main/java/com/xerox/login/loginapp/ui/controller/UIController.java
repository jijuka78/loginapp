package com.xerox.login.loginapp.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "userId";
	}
	
	@PostMapping("/getUser")
	public String getUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId,
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		User user = uIService.retrieveUser(userId);
		modelMap.put("message", user + " is fetched");
		return "menu";
	}
	
	@GetMapping("/createUser")
	public String addUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "user";
	}
	
	@PostMapping("/createUser")
	public String addUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId, 
			@RequestParam(value="password", required=true)  String password, 
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		modelMap.put("message", "User with Userid:" + userId  + "is created");
		uIService.createUser(new UserBean(userId, password));
		return "menu";
	}
	
	@GetMapping("/menu")
	public String showMenu(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "menu";
	}
	
	@GetMapping("/updateUser")
	public String updateUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "user";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId, 
			@RequestParam(value="password", required=true)  String password, 
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		modelMap.put("message", "User with Userid:" + userId  + "is updated");
		uIService.updateUser(new UserBean(userId, password), userId);
		return "menu";
	}
	
	@GetMapping("/removeUser")
	public String removeUser(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "userId";
	}
	
	@PostMapping("/removeUser")
	public String removeUser(@RequestParam(value="name", required=false)  String name, 
			@RequestParam(value="userId", required=true)  String userId,
			ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		modelMap.put("message", "User with Userid:" + userId  + "is removed");
		uIService.deleteUser(userId);
		return "menu";
	}
	
	
	@GetMapping("/listAllUsers")
	public String listAllUsers(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		
		List<User> users = uIService.retrieveAllUsers();
		modelMap.put("users", users);
		return "users";
	}
	
	@PostMapping("/listAllUsers")
	public String goBacktoMenu(@RequestParam(value="name", required=false)  String name, ModelMap  modelMap) {
		logger.debug("name=" + name);
		modelMap.put("name", (name != null && name.length() > 0)? name.trim().toUpperCase():"you");
		return "menu";
	}
}
