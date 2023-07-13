package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.RatingDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Rating;
import com.project.demo.model.Restaurant;
import com.project.demo.model.User;
import com.project.demo.serviceinterface.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	private RatingDAO ratingDao;
	private RestaurantDAO restaurantDao;
	private UserDAO userDao;
	
	/**
	 * Constructor for RatingServiceImpl that mandates ratingDao, restaurantDao and userDao.
	 * @param ratingDao ratingDao class to perform CRUD operations
	 * @param restaurantDao restaurantDao class to perform CRUD operations
	 * @param userDao userDao class to perform CRUD operations
	 */
	public RatingServiceImpl(RatingDAO ratingDao, RestaurantDAO restaurantDao, UserDAO userDao) {
		super();
		this.ratingDao = ratingDao;
		this.restaurantDao = restaurantDao;
		this.userDao = userDao;
	}
	
	@Override
	public Rating addRating(Rating rating, long restaurantId, long userId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "restaurent not found"
				));		
		User user = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "user not found"
				));
		rating.setRestaurent(restaurant);
		rating.setUser(user);

		Rating savedRating = ratingDao.save(rating);

		restaurant.getRatings().add(savedRating);
		user.getRatings().add(savedRating);
		
		restaurantDao.save(restaurant);
		userDao.save(user);
		
		return savedRating;
		
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingDao.findAll();
	}

	@Override
	public Rating getRating(long ratingId) {
		return ratingDao.findById(ratingId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Rating found with id = %d", ratingId)));
	}

	@Override
	public Rating updateRating(Rating rating, long ratingId) {
		Rating RatingExtracted = ratingDao.findById(ratingId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Rating found with id = %d", ratingId)));
		RatingExtracted.setValue(rating.getValue());
		return ratingDao.save(RatingExtracted);
	}

	@Override
	public void deleteRating(long ratingId) {
		Rating ratingExtracted = ratingDao.findById(ratingId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No Rating found with id = %d", ratingId)));
		Restaurant restaurent = ratingExtracted.getRestaurent();
		restaurent.getRatings().remove(ratingExtracted);
		restaurantDao.save(restaurent);
		
		User user = ratingExtracted.getUser();
		user.getRatings().remove(ratingExtracted);
		userDao.save(user);
		
		ratingDao.delete(ratingExtracted);
	}

}
