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

import com.project.demo.model.MenuItem;
import com.project.demo.serviceinterface.MenuItemService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for MenuItems is /api/v1/menu-item.
 * This represents a Controller class for menuItems. 
 * This class provides CRUD operation for the resource "MenuItem".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/api/v1/menu-item/restaurant/{restaurantId}/food/{foodId}</td>
 * <td>Adds menuItem to DB and links it with Food and Restaurant. 
 * Expects the menuItem to be present in the body and restaurant id and
 * Food id provided in the path variable.</td></tr>
 * <tr><td>GET</td><td>/api/v1/menu-item</td><td>Fetches all the menuItems in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/menu-item/{menuItemId}</td>
 * <td>Fetches the menuItem whose Id is mentioned in the PathVariable.</td></tr>
 * <tr><td>PUT</td><td>/api/v1/menu-item/{menuItemId}</td>
 * <td>Updates the menuItem with new menuItem provided in the body.</td>
 * <tr><td>DELETE</td><td>/api/v1/menu-item/{menuItemId}</td>
 * <td>Deletes the menuItem whose id is provided in the path variable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping(path = "/api/v1/menu-item", consumes = {"application/json"}, produces = {"application/json", "application/xml"})
public class MenuItemController {

	
	private MenuItemService menuItemService;

	/**
	 * Constructor based Dependency Injection for menuItem service.
	 * @param menuItemService service that is needed to perform CRUD operations.
	 */
	public MenuItemController(MenuItemService menuItemService) {
		super();
		this.menuItemService = menuItemService;
	}

	
	/**
	 * This Method Fetches all the menuItems from DB.
	 * 
	 * @return a list of comments from database.
	 */
	@GetMapping("/")
	public List<MenuItem> getAllMenuItems() {
		return menuItemService.getAllMenuItems();
	}
	
	/**
	 * Fetches the information of menuItem whose ID is provided.
	 * @param menuItemId Id of the menuItem whose info needs to be fetched.
	 * @return the Info of menuItem that needs to be fetched.
	 */
	@GetMapping("/{menuItemId}")
	public MenuItem getMenuItem(@PathVariable("menuItemId") long menuItemId) {
		return menuItemService.getMenuItem(menuItemId);
	}
	
	/**
	 * Adds MenuItem to the DB and links it with appropriate restaurant and food.
	 * @param menuItem MenuItem data sent as a part of body.
	 * @param restaurantId Id of the restaurant to which menuItem needs to be associated.
	 * @param foodId Id of the food to which menuItem needs to be associated.
	 * @return the menuItem that is created on the database.
	 */
	@PostMapping("/restaurant/{restaurantId}/food/{foodId}")
	public MenuItem createMenuItem(
			@Valid @RequestBody MenuItem menuItem, 
			@PathVariable("restaurantId") long restaurantId, 
			@PathVariable("foodId") long foodId
			) {
		return menuItemService.addMenuItem(menuItem, restaurantId, foodId);
	}
	

	/**
	 * Updates the MenuItem with a new menuItem provided in the body.
	 * @param menuItem New menuItem
	 * @param menuItemId ID of the menuItem that must be updated.
	 * @return updated menuItem stored on DB
	 */
	@PutMapping("/{menuItemId}")
	public MenuItem updateMenuItem(@Valid @RequestBody MenuItem menuItem, @PathVariable("menuItemId") long menuItemId) {
		return menuItemService.updateMenuItem(menuItem, menuItemId);
	}
	
	/**
	 * Deletes a MenuItem whose id is provided.
	 * @param menuItemId ID of the menuItem that must be deleted.
	 */
	@DeleteMapping("/{menuItemId}")
	public void deleteComment(@PathVariable("menuItemId") long menuItemId) {
		menuItemService.deleteMenuItem(menuItemId);
	}

}
