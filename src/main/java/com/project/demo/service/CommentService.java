package com.project.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.CommentDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Comment;
import com.project.demo.model.Restaurant;
import com.project.demo.model.User;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO commentDao;
	@Autowired
	private RestaurantDAO restaurantDao;
	@Autowired
	private UserDAO userDao;
	
	/**
	 * Adds comment to the DB and links it with appropriate restaurant and user.
	 * @param comment Comment data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which comment needs to be associated.
	 * @param userId Id of the user to which comment needs to be associated.
	 * @return the comment that is created on the database.
	 */
	public Comment addComment(Comment comment, long restaurantId, long userId) {
		
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
	 * This Method Fetches all the comments from DB.
	 * 
	 * @return a list of comments from database.
	 */
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}
	
	/**
	 * Fetches the information of Comment whose ID is provided.
	 * @param commentId Id of the comment whose info needs to be fetched.
	 * @return the Info of comment that needs to be fetched.
	 */
	public Comment getComment(long commentId) {
		return commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
	}

	/**
	 * Updates the comment with a new comment provided in the body.
	 * @param comment New Comment
	 * @param commentId ID of the comment that must be updated.
	 * @return updated comment stored on DB
	 */
	public Comment updateComment(Comment comment, long commentId) {
		Comment commentExtracted = commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
		commentExtracted.setComment(comment.getComment());
		return commentDao.save(commentExtracted);
	}

	/**
	 * Deletes a comment whose id is provided.
	 * @param commentId ID of the comment that must be deleted.
	 */
	public void deleteComment(long commentId) {
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
