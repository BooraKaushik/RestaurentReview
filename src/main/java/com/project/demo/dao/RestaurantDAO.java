package com.project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.demo.model.Comment;
import com.project.demo.model.Restaurant;

public interface RestaurantDAO extends JpaRepository<Restaurant, Long> {
	
}
