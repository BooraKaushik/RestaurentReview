package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.MenuItem;
/**
 * This Interface provides all the methods to perform CRUD operations on MenuItem entity of DB.
 */
public interface MenuItemDAO extends JpaRepository<MenuItem, Long>{

}
