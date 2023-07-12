package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Comment;

/**
 * This Interface provides CRUD functionalities for Comment.
 */
public interface CommentService {
	
	/**
	 * Adds comment to the DB and links it with appropriate restaurant and user.
	 * @param comment Comment data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which comment needs to be associated.
	 * @param userId Id of the user to which comment needs to be associated.
	 * @return the comment that is created on the database.
	 */
	public Comment addComment(Comment comment, long restaurantId, long userId);
	
	/**
	 * This Method Fetches all the comments from DB.
	 * 
	 * @return a list of comments from database.
	 */
	public List<Comment> getAllComments();
	
	/**
	 * Fetches the information of Comment whose ID is provided.
	 * @param commentId Id of the comment whose info needs to be fetched.
	 * @return the Info of comment that needs to be fetched.
	 */
	public Comment getComment(long commentId);
	
	/**
	 * Updates the comment with a new comment provided in the body.
	 * @param comment New Comment
	 * @param commentId ID of the comment that must be updated.
	 * @return updated comment stored on DB
	 */
	public Comment updateComment(Comment comment, long commentId);
	
	/**
	 * Deletes a comment whose id is provided.
	 * @param commentId ID of the comment that must be deleted.
	 */
	public void deleteComment(long commentId);
}
