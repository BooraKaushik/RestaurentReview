package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Address;

/**
 * This Interface provides all the methods to perform CRUD operations on Address entity of DB.
 */
public interface AddressDAO extends JpaRepository<Address, Long> {

}
