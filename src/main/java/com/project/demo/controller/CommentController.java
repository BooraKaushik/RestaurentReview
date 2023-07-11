package com.project.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.CommentDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Comment;
import com.project.demo.model.Restaurant;
import com.project.demo.model.User;

/**
 * @author Kaushik Boora.
 * The API path for comments is /api/v1/comment.
 * This represents a Controller class for comments. 
 * This class provides CRUD operation for the resource "Comment".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/comment/restaurant/{restaurantId}/user/{userId}</td>
 * <td>Adds comment to DB and links it with User and Restaurant. 
 * Expects the comment to be present in the body and restaurant id and
 * user id provided in the path variable.</td></tr>
 * <tr><td>GET</td><td>/api/v1/comment</td><td>Fetches all the comments in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/comment/{commentId}</td>
 * <td>Fetches the comment whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/comment/{commentId}</td>
 * <td>Updates the comment with new comment provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/comment/{commentId}</td>
 * <td>Deletes the comment whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private RestaurantDAO restaurantDao;
	
	@Autowired
	private UserDAO userDao;

	/**
	 * This Method Fetches all the comments from DB.
	 * 
	 * @return a list of comments from database.
	 */
	@GetMapping("/")
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}

	/**
	 * Fetches the information of Comment whose ID is provided.
	 * @param commentId Id of the comment whose info needs to be fetched.
	 * @return the Info of comment that needs to be fetched.
	 */
	@GetMapping("/{commentId}")
	public Comment getComment(@RequestParam long commentId) {
		return commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
	}
	
	/**
	 * Adds comment to the DB and links it with appropriate restaurant and user.
	 * @param comment Comment data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which comment needs to be associated.
	 * @param userId Id of the user to which comment needs to be associated.
	 * @return the comment that is created on the database.
	 */
	@PostMapping("/restaurant/{restaurantId}/user/{userId}")
	public Comment createComment(
			@RequestBody Comment comment, 
			@PathVariable("restaurantId") long restaurantId, 
			@PathVariable("userId") long userId
			) {
		
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "restaurent not found"
				));		
		User user = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "user not found"
				));

		
		comment.setRestaurent(restaurant);
		comment.setUser(user);

		Comment savedComment = commentDao.save(comment);

		restaurant.getComments().add(savedComment);
		user.getComments().add(savedComment);
		
		restaurantDao.save(restaurant);
		userDao.save(user);
		
		return savedComment;
	}
	
	/**
	 * Updates the comment with a new comment provided in the body.
	 * @param comment New Comment
	 * @param commentId ID of the comment that must be updated.
	 * @return updated comment stored on DB
	 */
	@PutMapping("/{commentId}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable("commentId") long commentId) {
		Comment commentExtracted = commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
		commentExtracted.setComment(comment.getComment());
		return commentDao.save(commentExtracted);
	}
	
	/**
	 * Deletes a comment whose id is provided.
	 * @param commentId ID of the comment that must be deleted.
	 */
	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable("commentId") long commentId) {
		Comment commentExtracted = commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
		Restaurant restaurent = commentExtracted.getRestaurent();
		Set<Comment> restaurentComments = restaurent.getComments();
		restaurentComments.remove(commentExtracted);
		restaurantDao.save(restaurent);
		
		User user = commentExtracted.getUser();
		Set<Comment> userComments = user.getComments();
		userComments.remove(commentExtracted);
		userDao.save(user);
		
		commentDao.delete(commentExtracted);
		
	}

}
