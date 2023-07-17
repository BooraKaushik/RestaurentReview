package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Food;

/**
 * This Interface provides all the methods to perform CRUD operations on Food entity of DB.
 */
public interface FoodDAO extends JpaRepository<Food, Long> {

}
