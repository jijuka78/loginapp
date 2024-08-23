package com.xerox.login.loginapp.ui.service;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.ui.model.User;

@Service
public class UIServiceImpl implements UIService {
	
	@Autowired
	RestTemplate restTemplate;
	
    @Autowired
	private ModelMapper modelMapper;

	@Override
	public List<User> retrieveAllUsers() {
		
		String url ="/fetchUsers";
		UserBean[] userBeans = restTemplate.getForObject(url, UserBean[].class);
		User[] userArray = this.modelMapper.map(userBeans, User[].class);
		List<User> users = Arrays.asList(userArray);
		return users;
	}
	
//	@Override
//	public User retrieveUser(String userId) {
//		String url ="/users";
//		UserBean userBeans = restTemplate.getForObject(url, UserBean.class);
//		User user = this.modelMapper.map(userBeans, User.class);
//		return user;
//	}

}
