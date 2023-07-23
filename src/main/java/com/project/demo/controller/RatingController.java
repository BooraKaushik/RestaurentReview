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

import com.project.demo.model.Rating;
import com.project.demo.serviceinterface.RatingService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for Ratings is /api/v1/rating.
 * This represents a Controller class for ratings. 
 * This class provides CRUD operation for the resource "Rating".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/rating/restaurant/{restaurantId}/user/{userId}</td>
 * <td>Adds rating to DB and links it with User and Restaurant. 
 * Expects the rating to be present in the body and restaurant id and
 * user id provided in the path variable.</td></tr>
 * <tr><td>GET</td><td>/api/v1/rating</td><td>Fetches all the ratings in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/rating/{ratingId}</td>
 * <td>Fetches the rating whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/rating/{ratingId}</td>
 * <td>Updates the rating with new rating provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/rating/{ratingId}</td>
 * <td>Deletes the rating whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping(path = "/api/v1/rating", consumes = {"application/json"}, produces = {"application/json", "application/xml"})
public class RatingController {
	
	private final RatingService ratingService;

	/**
	 * Constructor based Dependency Injection for rating service.
	 * @param ratingService service that is needed.
	 */
	public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}

	
	/**
	 * This Method Fetches all the ratings from DB.
	 * 
	 * @return a list of comments from database.
	 */
	@GetMapping("/")
	public List<Rating> getAllRatings() {
		return ratingService.getAllRatings();
	}
	
	/**
	 * Fetches the information of rating whose ID is provided.
	 * @param ratingId Id of the rating whose info needs to be fetched.
	 * @return the Info of rating that needs to be fetched.
	 */
	@GetMapping("/{ratingId}")
	public Rating getRating(@PathVariable("ratingId") long ratingId) {
		return ratingService.getRating(ratingId);
	}
	
	/**
	 * Adds Rating to the DB and links it with appropriate restaurant and user.
	 * @param rating Rating data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which rating needs to be associated.
	 * @param userId Id of the user to which rating needs to be associated.
	 * @return the rating that is created on the database.
	 */
	@PostMapping("/restaurant/{restaurantId}/user/{userId}")
	public Rating createRating(
			@Valid @RequestBody Rating rating, 
			@PathVariable("restaurantId") long restaurantId, 
			@PathVariable("userId") long userId
			) {
		return ratingService.addRating(rating, restaurantId, userId);
	}
	

	/**
	 * Updates the Rating with a new rating provided in the body.
	 * @param rating New rating
	 * @param ratingId ID of the rating that must be updated.
	 * @return updated rating stored on DB
	 */
	@PutMapping("/{ratingId}")
	public Rating updateRating(@Valid @RequestBody Rating rating, @PathVariable("ratingId") long ratingId) {
		return ratingService.updateRating(rating, ratingId);
	}
	
	/**
	 * Deletes a Rating whose id is provided.
	 * @param ratingId ID of the rating that must be deleted.
	 */
	@DeleteMapping("/{ratingId}")
	public void deleteComment(@PathVariable("ratingId") long ratingId) {
		ratingService.deleteRating(ratingId);
	}

}
