package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.ContactDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Contact;
import com.project.demo.model.Restaurant;
import com.project.demo.model.User;
import com.project.demo.serviceinterface.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	private final ContactDAO contactDao;
	private final RestaurantDAO restaurantDao;
	private final UserDAO userDao;

    public ContactServiceImpl(ContactDAO contactDao, RestaurantDAO restaurantDao, UserDAO userDao) {
    	super();
        this.contactDao = contactDao;
        this.restaurantDao = restaurantDao;
        this.userDao = userDao;
    }

	@Override
	public Contact addRestaurantContact(Contact contact, long restaurantId) {
		Restaurant extractedRestaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Restaurant not found"
				));
		Contact savedContact = contactDao.save(contact);
		extractedRestaurant.setContact(savedContact);
		restaurantDao.save(extractedRestaurant);
		return savedContact;
	}
	
	@Override
	public Contact addUserContact(Contact contact, long userId) {
		User extractedUser = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
		Contact savedContact = contactDao.save(contact);
		extractedUser.setContact(savedContact);
		userDao.save(extractedUser);
		return savedContact;
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactDao.findAll();
	}

	@Override
	public Contact getContact(long contactId) {
		return contactDao.findById(contactId).orElseThrow(()->new ResourceNotFoundException(String.format("No contact found with id = %d", contactId)));
	}

	@Override
	public Contact updateContact(Contact contact, long contactId) {
		Contact fetchedContact = contactDao.findById(contactId).orElseThrow(()->new ResourceNotFoundException(String.format("No contact found with id = %d", contactId)));
		if(contact.getEmail() != null) {
			fetchedContact.setEmail(contact.getEmail());
		}
		if(contact.getPhoneNumber() != null) {
			fetchedContact.setPhoneNumber(contact.getPhoneNumber());
		}
		return contactDao.save(fetchedContact);
	}

	@Override
	public void deleteContact(long contactId) {
		contactDao.deleteById(contactId);
	}

    

}
