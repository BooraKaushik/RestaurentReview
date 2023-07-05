package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long> {

}
