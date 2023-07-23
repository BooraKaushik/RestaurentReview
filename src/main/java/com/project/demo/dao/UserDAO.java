package com.project.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.User;

public interface UserDAO extends JpaRepository<User, Long> {
	
	public Optional<User> findByUserName(String userName);
}
