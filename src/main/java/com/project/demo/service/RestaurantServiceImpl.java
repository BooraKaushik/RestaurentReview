package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.RestaurantDAO;
import com.project.demo.model.Restaurant;
import com.project.demo.serviceinterface.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantDAO restaurantDao;

	/**
	 * Constructor for RestaurantServiceImpl that mandates restaurantDao.
	 * @param restaurantDao restaurantDao class to perform CRUD operations.
	 */
	public RestaurantServiceImpl(RestaurantDAO restaurantDao) {
		super();
		this.restaurantDao = restaurantDao;
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantDao.save(restaurant);
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantDao.findAll();
	}

	@Override
	public Restaurant getRestaurant(long restaurantId) {
		return restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Restaurant not found"
				));
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant, long restaurantId) {
		Restaurant RestaurantExtracted = restaurantDao.findById(restaurantId).orElseThrow(
			() -> new ResourceNotFoundException(String.format("No restaurant found with id = %d", restaurantId)));
		if(restaurant.getName() != null) {
			RestaurantExtracted.setName(restaurant.getName());
		}
		if(restaurant.getCusine() != null) {
			RestaurantExtracted.setCusine(restaurant.getCusine());
		}
	return restaurantDao.save(RestaurantExtracted);
	}

	@Override
	public void deleteRestaurant(long restaurantId) {
		restaurantDao.deleteById(restaurantId);		
	}

}
