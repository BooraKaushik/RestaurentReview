package com.project.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.FoodDAO;
import com.project.demo.dao.MenuItemDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.model.Food;
import com.project.demo.model.MenuItem;
import com.project.demo.model.Restaurant;
import com.project.demo.serviceinterface.MenuItemService;

/**
 * This is a Service Class for MenuItem providing CRUD operations.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

	
	private FoodDAO foodDao;
	private MenuItemDAO menuItemDao;
	private RestaurantDAO restaurantDao;

	/**
	 * Constructor for MenuItemServiceImpl that mandates foodDao, restaurantDao and menuItemDao.
	 * @param foodDao foodDao class to perform CRUD operations
	 * @param restaurantDao restaurantDao class to perform CRUD operations
	 * @param menuItemDao menuItemDao class to perform CRUD operations
	 */
	public MenuItemServiceImpl(FoodDAO foodDao, MenuItemDAO menuItemDao, RestaurantDAO restaurantDao) {
		this.foodDao = foodDao;
		this.menuItemDao = menuItemDao;
		this.restaurantDao = restaurantDao;
	}

	@Override
	public MenuItem addMenuItem(MenuItem menuItem, long restaurantId, long foodId) {
		
		Restaurant restaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Restaurant not found"
				));
		
		Food food = foodDao.findById(foodId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Food not found"
				));	
		
		menuItem.setRestaurant(restaurant);
		menuItem.setFood(food);
		
		MenuItem createdMenuItem = menuItemDao.save(menuItem);
		
		restaurant.getMenu().add(createdMenuItem);
		restaurantDao.save(restaurant);
		
		return createdMenuItem;
	}

	@Override
	public List<MenuItem> getAllMenuItems() {
		return menuItemDao.findAll();
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		return menuItemDao.findById(menuItemId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "MenuItem not found"
				));
	}

	@Override
	public MenuItem updateMenuItem(MenuItem menuItem, long menuItemId) {
		
		MenuItem menuItemExtracted = menuItemDao.findById(menuItemId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "MenuItem not found"
				));
		
		if(menuItem.getPrice() > 0) {
			menuItemExtracted.setPrice(menuItem.getPrice());
		}
		if(menuItem.getCalories() > 0) {
			menuItemExtracted.setCalories(menuItem.getCalories());
		}
		
		return menuItemDao.save(menuItemExtracted);
	}

	@Override
	public void deleteMenuItem(long menuItemId) {
		MenuItem extractedMenuItem = menuItemDao.findById(menuItemId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "MenuItem not found"
				));
		Restaurant restaurant = extractedMenuItem.getRestaurant();
		restaurant.getMenu().remove(extractedMenuItem);
		restaurantDao.save(restaurant);
		menuItemDao.delete(extractedMenuItem);
		}
}
