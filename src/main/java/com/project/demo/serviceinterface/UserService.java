package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.dto.UserDTO;
import com.project.demo.dto.UserEditDTO;
import com.project.demo.model.User;

/**
 * This Interface provides CRUD functionalities for User.
 */
public interface UserService {
	
	/**
	 * Adds User to the DB.
	 * @param user User data sent as a part of body.
	 * @return the user that is created on the database.
	 */
	public UserDTO addUser(UserDTO user);

	/**
	 * This Method Fetches all the Users from DB.
	 * 
	 * @return a list of Users from database.
	 */
	public List<UserDTO> getAllUsers();
	
	/**
	 * Fetches the information of User whose ID is provided.
	 * @param userId Id of the User whose info needs to be fetched.
	 * @return the Info of User that needs to be fetched.
	 */
	public UserDTO getUser(long userId);
	

	/**
	 * Fetches the information of User whose user name is provided.
	 * @param userName userName of the User whose info needs to be fetched.
	 * @return the Info of User that needs to be fetched.
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * Updates the User with a new User provided in the body.
	 * @param user New User
	 * @param userId ID of the User that must be updated.
	 * @return updated User stored on DB
	 */
	public UserDTO updateUser(UserEditDTO user, long userId);
	
	/**
	 * Deletes a User whose id is provided.
	 * @param userId ID of the User that must be deleted.
	 */
	public void deleteUser(long userId);
}
