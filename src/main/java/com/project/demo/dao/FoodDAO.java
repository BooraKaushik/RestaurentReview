package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Food;

public interface FoodDAO extends JpaRepository<Food, Long> {

}
