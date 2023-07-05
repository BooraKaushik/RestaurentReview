package com.project.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Address;

public interface AddressDAO extends JpaRepository<Address, Long> {

}
