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

import com.project.demo.model.Food;
import com.project.demo.service.FoodServiceImpl;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for foods is /api/v1/food.
 * This represents a Controller class for foods. 
 * This class provides CRUD operation for the resource "Food".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/food/</td>
 * <td>Adds food to DB.</td></tr>
 * <tr><td>GET</td><td>/api/v1/food</td><td>Fetches all the foods in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/food/{foodId}</td>
 * <td>Fetches the food whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/food/{foodId}</td>
 * <td>Updates the food with new food provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/food/{foodId}</td>
 * <td>Deletes the food whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/food")
public class FoodController {


	private final FoodServiceImpl foodService;

	/**
	 * Constructor based Dependency Injection for Food service.
	 * @param foodService service that is needed.
	 */
	public FoodController(FoodServiceImpl foodService) {
		super();
		this.foodService = foodService;
	}

	/**
	 * This Method Fetches all the foods from DB.
	 * 
	 * @return a list of foods from database.
	 */
	@GetMapping("/")
	public List<Food> getAllFoods() {
		return foodService.getAllFoods();
	}

	/**
	 * Fetches the information of Food whose ID is provided.
	 * @param foodId Id of the food whose info needs to be fetched.
	 * @return the Info of food that needs to be fetched.
	 */
	@GetMapping("/{foodId}")
	public Food getFood(@PathVariable("foodId") long foodId) {
		return foodService.getFood(foodId);
	}
	
	/**
	 * Adds food to the DB and links it with appropriate restaurant and user.
	 * @param food Food data sent as a part of body.
	 * @return the food that is created on the database.
	 */
	@PostMapping("/")
	public Food createFood(@Valid @RequestBody Food food) {
		return foodService.addFood(food);
	}
	
	/**
	 * Updates the food with a new food provided in the body.
	 * @param food New Food
	 * @param foodId ID of the food that must be updated.
	 * @return updated food stored on DB
	 */
	@PutMapping("/{foodId}")
	public Food updateFood(@Valid @RequestBody Food food, @PathVariable("foodId") long foodId) {
		return foodService.updateFood(food, foodId);
	}
	
	/**
	 * Deletes a food whose id is provided.
	 * @param foodId ID of the food that must be deleted.
	 */
	@DeleteMapping("/{foodId}")
	public void deleteFood(@PathVariable("foodId") long foodId) {
		foodService.deleteFood(foodId);
	}

}
