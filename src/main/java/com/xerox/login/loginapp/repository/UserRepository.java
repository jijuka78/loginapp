package com.xerox.login.loginapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xerox.login.loginapp.model.UserBean;

public interface UserRepository extends JpaRepository<UserBean, Integer>{
	
	UserBean findByUserId(String userId);

}
