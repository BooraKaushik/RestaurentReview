package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dao.UserDAO;
import com.project.demo.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
	
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user){
		return userDao.save(user);
	}
}
