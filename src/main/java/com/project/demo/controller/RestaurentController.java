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

import com.project.demo.model.Restaurant;
import com.project.demo.serviceinterface.RestaurantService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for Restaurants is /api/v1/restaurant.
 * This represents a Controller class for restaurants. 
 * This class provides CRUD operation for the resource "Restaurant".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/restaurant/</td>
 * <td>Adds restaurant to DB. 
 * Expects the restaurant to be present in the body.</td></tr>
 * <tr><td>GET</td><td>/api/v1/restaurant</td><td>Fetches all the restaurants in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/restaurant/{restaurantId}</td>
 * <td>Fetches the restaurant whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/restaurant/{restaurantId}</td>
 * <td>Updates the restaurant with new restaurant provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/restaurant/{restaurantId}</td>
 * <td>Deletes the restaurant whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurentController {
	
	private final RestaurantService restaurantService;
	
	/**
	 * Constructor based Dependency Injection for restaurant service.
	 * @param restaurantService service that is needed.
	 */
	public RestaurentController(RestaurantService restaurantService) {
		super();
		this.restaurantService = restaurantService;
	}

	
	/**
	 * This Method Fetches all the restaurants from DB.
	 * 
	 * @return a list of comments from database.
	 */
	@GetMapping("/")
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	/**
	 * Fetches the information of restaurant whose ID is provided.
	 * @param restaurantId Id of the restaurant whose info needs to be fetched.
	 * @return the Info of restaurant that needs to be fetched.
	 */
	@GetMapping("/{restaurantId}")
	public Restaurant getRestaurant(@PathVariable("restaurantId") long restaurantId) {
		return restaurantService.getRestaurant(restaurantId);
	}
	
	/**
	 * Adds Restaurant to the DB.
	 * @param restaurant Restaurant data sent as a part of body.
	 * @return the restaurant that is created on the database.
	 */
	@PostMapping("/")
	public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}
	

	/**
	 * Updates the Restaurant with a new restaurant provided in the body.
	 * @param restaurant New restaurant
	 * @param restaurantId ID of the restaurant that must be updated.
	 * @return updated restaurant stored on DB
	 */
	@PutMapping("/{restaurantId}")
	public Restaurant updateRestaurant(
			@Valid @RequestBody Restaurant restaurant, 
			@PathVariable("restaurantId") long restaurantId
			) {
		return restaurantService.updateRestaurant(restaurant, restaurantId);
	}
	
	/**
	 * Deletes a Restaurant whose id is provided.
	 * @param restaurantId ID of the restaurant that must be deleted.
	 */
	@DeleteMapping("/{restaurantId}")
	public void deleteComment(@PathVariable("restaurantId") long restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
	}

}
