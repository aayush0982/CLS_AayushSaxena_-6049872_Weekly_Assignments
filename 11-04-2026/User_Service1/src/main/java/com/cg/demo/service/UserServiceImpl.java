package com.cg.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.demo.exception.UserNotFoundException;
import com.cg.demo.model.UserService;
import com.cg.demo.repo.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserRepo userRepo;

	@Override
	public void addUser(UserService user) {
		// TODO Auto-generated method stub
		userRepo.save(user);

	}

	@Override
	public UserService getUserById(Integer userId) throws UserNotFoundException {
		UserService user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
		return user;
	}

}
