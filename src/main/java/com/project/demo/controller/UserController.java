package com.project.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.User;
import com.project.demo.serviceinterface.UserService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for Users is /api/v1/user.
 * This represents a Controller class for users. 
 * This class provides CRUD operation for the resource "User".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/user/</td>
 * <td>Adds user to DB. 
 * Expects the user to be present in the body.</td></tr>
 * <tr><td>GET</td><td>/api/v1/user</td><td>Fetches all the users in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/user/{userId}</td>
 * <td>Fetches the user whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/user/{userId}</td>
 * <td>Updates the user with new user provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/user/{userId}</td>
 * <td>Deletes the user whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private UserService userService;
	
	/**
	 * Constructor based Dependency Injection for user service.
	 * @param userService service that is needed.
	 */
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	
	/**
	 * This Method Fetches all the users from DB.
	 * 
	 * @return a list of comments from database.
	 */
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/**
	 * Fetches the information of user whose ID is provided.
	 * @param userId Id of the user whose info needs to be fetched.
	 * @return the Info of user that needs to be fetched.
	 */
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") long userId) {
		return userService.getUser(userId);
	}
	
	/**
	 * Adds User to the DB.
	 * @param user User data sent as a part of body.
	 * @return the user that is created on the database.
	 */
	@PostMapping("/")
	public User createUser(@Valid @RequestBody User user) {
		return userService.addUser(user);
	}
	

	/**
	 * Updates the User with a new user provided in the body.
	 * @param user New user
	 * @param userId ID of the user that must be updated.
	 * @return updated user stored on DB
	 */
	@PutMapping("/{userId}")
	public User updateUser(
			@Valid @RequestBody User user, 
			@PathVariable("userId") long userId
			) {
		return userService.updateUser(user, userId);
	}
	
	/**
	 * Deletes a User whose id is provided.
	 * @param userId ID of the user that must be deleted.
	 */
	@DeleteMapping("/{userId}")
	public void deleteComment(@PathVariable("userId") long userId) {
		userService.deleteUser(userId);
	}

}