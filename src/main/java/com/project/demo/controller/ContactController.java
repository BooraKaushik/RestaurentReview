package com.project.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.Contact;
import com.project.demo.serviceinterface.ContactService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for Contact is /api/v1/contact.
 * This represents a Controller class for Contact. 
 * This class provides CRUD operation for the resource "Contact".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>PUT</td><td>/api/v1/contact</td>
 * <td>Updates the contact with the provided contact</td></tr>
 * <tr><td>GET</td><td>/api/v1/contact</td><td>Fetches all the contacts in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/contact/{contactId}</td>
 * <td>Fetches the contact whose Id is mentioned in the PathVariable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
	
	private final ContactService contactService;
	
	/**
	 * Constructor for Contact controller. Creates the controller object by taking contact service.
	 * @param contactService service class required to perform operations on contact entity.
	 */
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	/**
	 * This Method Fetches all the contacts from DB.
	 * 
	 * @return a list of contacts from database.
	 */
	@GetMapping("/")
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}

	/**
	 * Fetches the information of Contact whose ID is provided.
	 * @param contactId Id of the contact whose info needs to be fetched.
	 * @return the Info of contact that needs to be fetched.
	 */
	@GetMapping("/{contactId}")
	public Contact getContact(@PathVariable("contactId") long contactId) {
		return contactService.getContact(contactId);
	}
	
	/**
	 * Updates the contact with a new contact provided in the body.
	 * @param contact New Contact
	 * @param contactId ID of the contact that must be updated.
	 * @return updated contact stored on DB
	 */
	@PutMapping("/{contactId}")
	public Contact updateContact(@Valid @RequestBody Contact contact, @PathVariable("contactId") long contactId) {
		return contactService.updateContact(contact, contactId);
	}

}
