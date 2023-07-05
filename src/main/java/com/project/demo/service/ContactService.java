package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dao.ContactDAO;

@Service
public class ContactService {
	
	private final ContactDAO contactDao;

    @Autowired
    public ContactService(ContactDAO contactDao) {
        this.contactDao = contactDao;
    }
    

}
