package com.cg.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.model.UserService;
import com.cg.demo.service.UserServiceImpl;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/")
	public void addUser(@RequestBody UserService user) {
		userService.addUser(user);
	}

	@GetMapping("/{id}")
	public UserService getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}

}
