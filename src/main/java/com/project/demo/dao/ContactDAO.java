package com.project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.demo.model.Contact;

public interface ContactDAO extends JpaRepository<Contact, Long>{
	
	public List<Contact> findByPhoneNumberAndEmail(String phoneNumber, String email);

}
