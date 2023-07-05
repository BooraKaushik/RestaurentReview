package com.project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.demo.model.Comment;
import com.project.demo.model.Restaurent;

public interface RestaurentDAO extends JpaRepository<Restaurent, Long> {

//	@Query("Select c from comment c where c.restaurent_id = :restaurentId")
//	public List<Comment> findByRestaurent_ID(@Param("restaurentId")long restaurentId);
}
