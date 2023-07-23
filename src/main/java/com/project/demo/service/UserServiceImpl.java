package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.UserDAO;
import com.project.demo.model.User;
import com.project.demo.serviceinterface.UserService;

/**
 * This is a Service Class for User providing CRUD operations.
 */
@Service
public class UserServiceImpl implements UserService {
	
	private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;

	/**
	 * Constructor for UserServiceImpl that mandates userDao.
	 * @param userDao userDao class to perform CRUD operations.
	 */
	public UserServiceImpl(UserDAO userDao, PasswordEncoder passwordEncoder) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(long userId) {
		return userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.findByUserName(userName).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
	}

	@Override
	public User updateUser(User user, long userId) {
		User UserExtracted = userDao.findById(userId).orElseThrow(
			() -> new ResourceNotFoundException(String.format("No user found with id = %d", userId)));
		if(user.getFirstName() != null) {
			UserExtracted.setFirstName(user.getFirstName());
		}
		if(user.getLastName() != null) {
			UserExtracted.setLastName(user.getLastName());
		}
		if(user.getDob() != null) {
			UserExtracted.setDob(user.getDob());
		}
		if(user.getGender() != null) {
			UserExtracted.setGender(user.getGender());
		}
	return userDao.save(UserExtracted);
	}

	@Override
	public void deleteUser(long userId) {
		userDao.deleteById(userId);		
	}

}
