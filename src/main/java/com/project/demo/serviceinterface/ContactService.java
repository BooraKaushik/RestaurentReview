package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Contact;

/**
 * This Interface provides CRUD functionalities for Contact.
 */
public interface ContactService {
	
	/**
	 * Adds contact to the DB.
	 * @param contact Contact data sent as a part of body.
	 * @return the contact that is created on the database.
	 */
	public Contact addContact(Contact contact);
	/**
	 * This Method Fetches all the contacts from DB.
	 * 
	 * @return a list of contacts from database.
	 */
	public List<Contact> getAllContacts();
	
	/**
	 * Fetches the information of Contact whose ID is provided.
	 * @param contactId Id of the contact whose info needs to be fetched.
	 * @return the Info of contact that needs to be fetched.
	 */
	public Contact getContact(long contactId);
	
	/**
	 * Updates the contact with a new contact provided in the body.
	 * @param contact New Contact
	 * @param contactId ID of the contact that must be updated.
	 * @return updated contact stored on DB
	 */
	public Contact updateContact(Contact contact, long contactId);
	
	/**
	 * Deletes a contact whose id is provided. Make sure that reference to the associated entity is deleted first.
	 * @param contactId ID of the contact that must be deleted.
	 */
	public void deleteContact(long contactId);
}
