package com.xerox.login.loginapp.ui.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xerox.login.loginapp.exception.UserNotFoundException;
import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.ui.config.UserUIConfiguration;
import com.xerox.login.loginapp.ui.model.User;


@Service
public class UIServiceImpl implements UIService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
    @Autowired
	private ModelMapper modelMapper;
    
    @Autowired
    private UserUIConfiguration userConfig;
    
	@Override
	public List<User> retrieveAllUsers() {
		String url =userConfig.getUrl() + "/users";
		UserBean[] userBeans = restTemplate.getForObject(url, UserBean[].class);
		User[] userArray = this.modelMapper.map(userBeans, User[].class);
		List<User> users = Arrays.asList(userArray);
		return users;
	}
	
	
	@Override
	public User retrieveUser(String userId) {
		String url = userConfig.getUrl() + "/user/" + userId;
		UserBean userBeans = restTemplate.getForObject(url, UserBean.class);
		User user = null;
		if(userBeans != null) {
			user = this.modelMapper.map(userBeans, User.class);
		}
		return user;
	}
	
	@Override
	public User createUser(UserBean user) {
		String url = userConfig.getUrl() + "/user";
		UserBean userBeans = restTemplate.postForObject(url, user, UserBean.class);
		User newUser = this.modelMapper.map(userBeans, User.class);
		return newUser;
	}

	@Override
	public User updateUser(UserBean user, String userId) {
		String url = userConfig.getUrl() + "/user/" + userId;
		restTemplate.put(url, user);

		User newUser = this.modelMapper.map(user, User.class);
		return newUser;
	}
	
	@Override
	public void deleteUser(String userId) {
		String url = userConfig.getUrl() + "/user/" + userId;
		restTemplate.delete(url);
	}
}
