package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.dao.FoodDAO;
import com.project.demo.model.Food;
import com.project.demo.serviceinterface.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	private final FoodDAO foodDao;

	/**
	 * Constructor for FoodServiceImpl class with mandatory argument FoodDAO.
	 * @param foodDao FoodDAO to perform CRUD operations.
	 */
    public FoodServiceImpl(FoodDAO foodDao) {
    	super();
        this.foodDao = foodDao;
    }

	@Override
	public Food addFood(Food food) {
		return foodDao.save(food);
	}

	@Override
	public List<Food> getAllFoods() {
		return foodDao.findAll();
	}

	@Override
	public Food getFood(long foodId) {
		return foodDao.findById(foodId).orElseThrow(()->new ResourceNotFoundException(String.format("No food found with id = %d", foodId)));
	}

	@Override
	public Food updateFood(Food food, long foodId) {
		Food fetchedFood = foodDao.findById(foodId).orElseThrow(()->new ResourceNotFoundException(String.format("No food found with id = %d", foodId)));
		if(food.getName() != null) {
			fetchedFood.setName(food.getName());
		}
		if(food.getIngredients() != null) {
			fetchedFood.setIngredients(food.getIngredients());
		}
		if(food.getCusine() != null) {
			fetchedFood.setCusine(food.getCusine());
		}
		return foodDao.save(fetchedFood);
	}

	@Override
	public void deleteFood(long foodId) {
		foodDao.deleteById(foodId);
	}
}
