package com.project.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.UserDAO;
import com.project.demo.dto.UserDTO;
import com.project.demo.dto.UserEditDTO;
import com.project.demo.model.User;
import com.project.demo.serviceinterface.UserService;

/**
 * This is a Service Class for User providing CRUD operations.
 */
@Service
public class UserServiceImpl implements UserService {
	
	private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	/**
	 * Constructor for UserServiceImpl that mandates userDao.
	 * @param userDao userDao class to perform CRUD operations.
	 */
	public UserServiceImpl(UserDAO userDao, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO addUser(UserDTO user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userDao.save(this.modelMapper.map(user, User.class));		
		return this.modelMapper.map(savedUser, UserDTO.class) ;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> data = userDao.findAll();
		List<UserDTO> responseData = data
										.stream()
										.map(user -> this.modelMapper.map(user, UserDTO.class))
										.collect(Collectors.toList());
		return responseData;
	}

	@Override
	public UserDTO getUser(long userId) {
		User extractedUser = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
		UserDTO responseData = this.modelMapper.map(extractedUser, UserDTO.class);
		return responseData;
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.findByUserName(userName).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
	}

	@Override
	public UserDTO updateUser(UserEditDTO user, long userId) {
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
		User userSaved = userDao.save(UserExtracted);
		return this.modelMapper.map(userSaved, UserDTO.class);
	}

	@Override
	public void deleteUser(long userId) {
		userDao.deleteById(userId);		
	}

}
