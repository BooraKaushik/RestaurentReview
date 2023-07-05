package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.MenuItem;

public interface MenuItemDAO extends JpaRepository<MenuItem, Long>{

}
