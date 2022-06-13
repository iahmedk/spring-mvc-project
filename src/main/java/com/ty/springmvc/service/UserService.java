package com.ty.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.springmvc.dao.UserDao;
import com.ty.springmvc.dto.User;

@Component
public class UserService {
	@Autowired
	private UserDao dao;

	public User saveUser(User user) {
		return dao.saveUser(user);
	}

	public void deleteUser(User user) {

	}

	public User validateUser(String email, String pwd) {
		return dao.validateUser(email, pwd);
	}
}
