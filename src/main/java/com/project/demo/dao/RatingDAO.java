package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Rating;

/**
 * This Interface provides all the methods to perform CRUD operations on Rating entity of DB.
 */
public interface RatingDAO extends JpaRepository<Rating, Long> {

}
