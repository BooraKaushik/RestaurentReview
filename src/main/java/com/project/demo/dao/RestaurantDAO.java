package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Restaurant;

public interface RestaurantDAO extends JpaRepository<Restaurant, Long> {
	
}
