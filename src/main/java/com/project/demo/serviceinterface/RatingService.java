package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Rating;

/**
 * This Interface provides CRUD functionalities for Rating.
 */
public interface RatingService {
	
	/**
	 * Adds Rating to the DB and links it with appropriate restaurant and user.
	 * @param rating Rating data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which rating needs to be associated.
	 * @param userId Id of the user to which rating needs to be associated.
	 * @return the rating that is created on the database.
	 */
	public Rating addRating(Rating rating, long restaurantId, long userId);

	/**
	 * This Method Fetches all the Ratings from DB.
	 * 
	 * @return a list of Ratings from database.
	 */
	public List<Rating> getAllRatings();
	
	/**
	 * Fetches the information of Rating whose ID is provided.
	 * @param ratingId Id of the Rating whose info needs to be fetched.
	 * @return the Info of Rating that needs to be fetched.
	 */
	public Rating getRating(long ratingId);
	
	/**
	 * Updates the Rating with a new Rating provided in the body.
	 * @param rating New Rating
	 * @param ratingId ID of the Rating that must be updated.
	 * @return updated Rating stored on DB
	 */
	public Rating updateRating(Rating rating, long ratingId);
	
	/**
	 * Deletes a Rating whose id is provided.
	 * @param ratingId ID of the Rating that must be deleted.
	 */
	public void deleteRating(long ratingId);
}
