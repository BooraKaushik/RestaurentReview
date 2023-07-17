package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dao.RestaurantDAO;
import com.project.demo.model.Comment;
import com.project.demo.model.Restaurant;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurentController {
	@Autowired
	private RestaurantDAO restaurentDao;
	
	@GetMapping("/")
	public List<Restaurant> getAllRestaurents() {
		return restaurentDao.findAll();
	}
	
	@GetMapping("/{restaurentId}/comments")
	public List<Comment> getAllCommentsOfRestaurent(@RequestParam long restaurentId) {
//		return restaurentDao.findByRestaurent_ID(restaurentId);
		return null;
	}
	
	@PostMapping("")
	public Restaurant createRestaurent(@Valid @RequestBody Restaurant restaurent) {
		return restaurentDao.save(restaurent);
	}

}
