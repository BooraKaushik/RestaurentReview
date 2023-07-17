package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Food;

/**
 * This Interface provides CRUD functionalities for Food.
 */
public interface FoodService {

	/**
	 * Adds food to the DB.
	 * @param food Food data sent as a part of body.
	 * @return the food that is created on the database.
	 */
	public Food addFood(Food food);
	/**
	 * This Method Fetches all the foods from DB.
	 * 
	 * @return a list of foods from database.
	 */
	public List<Food> getAllFoods();
	
	/**
	 * Fetches the information of Food whose ID is provided.
	 * @param foodId Id of the food whose info needs to be fetched.
	 * @return the Info of food that needs to be fetched.
	 */
	public Food getFood(long foodId);
	
	/**
	 * Updates the food with a new food provided in the body.
	 * @param food New Food
	 * @param foodId ID of the food that must be updated.
	 * @return updated food stored on DB
	 */
	public Food updateFood(Food food, long foodId);
	
	/**
	 * Deletes a food whose id is provided. Make sure that reference to the associated entity is deleted first.
	 * @param foodId ID of the food that must be deleted.
	 */
	public void deleteFood(long foodId);
}
