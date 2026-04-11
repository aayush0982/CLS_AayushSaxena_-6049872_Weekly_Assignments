package com.cg.demo.service;

import com.cg.demo.exception.UserNotFoundException;
import com.cg.demo.model.UserService;

public interface UserServiceInterface {
	public void addUser(UserService user);

	public UserService getUserById(Integer userId) throws UserNotFoundException;
}
