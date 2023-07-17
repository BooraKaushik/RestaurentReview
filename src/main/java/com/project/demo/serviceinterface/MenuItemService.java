package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.MenuItem;

/**
 * This Interface provides CRUD functionalities for MenuItem.
 */
public interface MenuItemService {
	

	/**
	 * Adds MenuItem to the DB and links it with appropriate food and Restaurant.
	 * @param menuItem MenuItem data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which menuItem needs to be associated.
	 * @param foodId Id of the food to which menuItem needs to be associated.
	 * @return the menuItem that is created on the database.
	 */
	public MenuItem addMenuItem(MenuItem menuItem, long restaurantId, long foodId);

	/**
	 * This Method Fetches all the MenuItems from DB.
	 * 
	 * @return a list of MenuItems from database.
	 */
	public List<MenuItem> getAllMenuItems();
	
	/**
	 * Fetches the information of MenuItem whose ID is provided.
	 * @param menuItemId Id of the MenuItem whose info needs to be fetched.
	 * @return the Info of MenuItem that needs to be fetched.
	 */
	public MenuItem getMenuItem(long menuItemId);
	
	/**
	 * Updates the MenuItem with a new MenuItem provided in the body. 
	 * Will only update price and calories not the restaurant.
	 * @param menuItem New MenuItem
	 * @param menuItemId ID of the MenuItem that must be updated.
	 * @return updated MenuItem stored on DB
	 */
	public MenuItem updateMenuItem(MenuItem menuItem, long menuItemId);
	
	/**
	 * Deletes a MenuItem whose id is provided.
	 * @param menuItemId ID of the MenuItem that must be deleted.
	 */
	public void deleteMenuItem(long menuItemId);
}
