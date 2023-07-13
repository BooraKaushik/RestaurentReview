package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.dao.ContactDAO;
import com.project.demo.model.Contact;
import com.project.demo.serviceinterface.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	
	private final ContactDAO contactDao;

    public ContactServiceImpl(ContactDAO contactDao) {
    	super();
        this.contactDao = contactDao;
    }

	@Override
	public Contact addContact(Contact contact) {
		return contactDao.save(contact);
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
