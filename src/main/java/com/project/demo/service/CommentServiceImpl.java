package com.project.demo.service;

import java.util.List;
import java.util.Set;

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
import com.project.demo.serviceinterface.CommentService;

/**
 * This is a Service Class for Comment providing CRUD operations.
 */
@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentDAO commentDao;
	private final RestaurantDAO restaurantDao;
	private final UserDAO userDao;
	
	/**
	 * Constructor for CommentServiceImpl that mandates commentDao, restaurantDao and userDao.
	 * @param commentDao CommentDao class to perform CRUD operations
	 * @param restaurantDao restaurantDao class to perform CRUD operations
	 * @param userDao userDao class to perform CRUD operations
	 */
	public CommentServiceImpl(CommentDAO commentDao, RestaurantDAO restaurantDao, UserDAO userDao) {
		super();
		this.commentDao = commentDao;
		this.restaurantDao = restaurantDao;
		this.userDao = userDao;
	}

	public Comment addComment(Comment comment, long restaurantId, long userId) {
		
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "restaurent not found"
				));		
		User user = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "user not found"
				));

		
		comment.setRestaurant(restaurant);
		comment.setUser(user);

		Comment savedComment = commentDao.save(comment);

		restaurant.getComments().add(savedComment);
		user.getComments().add(savedComment);
		
		restaurantDao.save(restaurant);
		userDao.save(user);
		
		return savedComment;
	}
	
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}
	
	public Comment getComment(long commentId) {
		return commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
	}

	public Comment updateComment(Comment comment, long commentId) {
		Comment commentExtracted = commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
		commentExtracted.setComment(comment.getComment());
		return commentDao.save(commentExtracted);
	}

	public void deleteComment(long commentId) {
		Comment commentExtracted = commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
		Restaurant restaurent = commentExtracted.getRestaurant();
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
