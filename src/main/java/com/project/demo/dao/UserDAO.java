package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.User;

public interface UserDAO extends JpaRepository<User, Long> {

}
