package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Rating;

public interface RatingDAO extends JpaRepository<Rating, Long> {

}
