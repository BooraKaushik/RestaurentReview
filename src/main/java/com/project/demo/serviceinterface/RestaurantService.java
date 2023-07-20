package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Restaurant;

/**
 * This Interface provides CRUD functionalities for Restaurant.
 */
public interface RestaurantService {

	/**
	 * Adds Restaurant to the DB.
	 * @param restaurant Restaurant data sent as a part of body.
	 * @return the restaurant that is created on the database.
	 */
	public Restaurant addRestaurant(Restaurant restaurant);

	/**
	 * This Method Fetches all the Restaurants from DB.
	 * 
	 * @return a list of Restaurants from database.
	 */
	public List<Restaurant> getAllRestaurants();
	
	/**
	 * Fetches the information of Restaurant whose ID is provided.
	 * @param restaurantId Id of the Restaurant whose info needs to be fetched.
	 * @return the Info of Restaurant that needs to be fetched.
	 */
	public Restaurant getRestaurant(long restaurantId);
	
	/**
	 * Updates the Restaurant with a new Restaurant provided in the body.
	 * @param restaurant New Restaurant
	 * @param restaurantId ID of the Restaurant that must be updated.
	 * @return updated Restaurant stored on DB
	 */
	public Restaurant updateRestaurant(Restaurant restaurant, long restaurantId);
	
	/**
	 * Deletes a Restaurant whose id is provided.
	 * @param restaurantId ID of the Restaurant that must be deleted.
	 */
	public void deleteRestaurant(long restaurantId);
}
