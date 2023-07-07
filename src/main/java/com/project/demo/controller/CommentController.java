package com.project.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.CommentDAO;
import com.project.demo.dao.RestaurentDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Comment;
import com.project.demo.model.Restaurent;
import com.project.demo.model.User;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private RestaurentDAO restaurentDao;
	
	@Autowired
	private UserDAO userDao;

	@GetMapping("/")
	public List<Comment> getAllComments() {
		return commentDao.findAll();
	}

	@GetMapping("/{commentId}")
	public Comment getRestaurentComments(@RequestParam long commentId) {
		return commentDao.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Comment found with id = %d", commentId)));
	}
	
	@PostMapping("/restaurent/{restaurentId}/user/{userId}")
	public Comment createComment(
			@RequestBody Comment comment, 
			@PathVariable("restaurentId") long restaurentId, 
			@PathVariable("userId") long userId
			) {
		
		Comment commentCreated = commentDao.save(comment);
		
		Restaurent restaurent = restaurentDao.findById(restaurentId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "restaurent not found"
				));
		Set<Comment> commentsRestaurent = restaurent.getComments();
		commentsRestaurent.add(commentCreated);
		restaurentDao.save(restaurent);
		User user = userDao.findById(userId).orElse(null);
		if(user == null) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "user not found"
					);
		}
		Set<Comment> commentsUser = user.getComments();
		commentsUser.add(commentCreated);
		userDao.save(user);
		return commentCreated;
	}

}
