package com.project.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.Address;
import com.project.demo.serviceinterface.AddressService;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * The API path for Address is /api/v1/address.
 * This represents a Controller class for Address. 
 * This class provides CRUD operation for the resource "Address".
 * This controller class provides following methods,
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>PUT</td><td>/api/v1/address</td>
 * <td>Updates the address with the provided address</td></tr>
 * <tr><td>GET</td><td>/api/v1/address</td><td>Fetches all the addresses in the Database.</td></tr>
 * <tr><td>GET</td><td>/api/v1/address/{addressId}</td>
 * <td>Fetches the address whose Id is mentioned in the PathVariable.</td>
 * </tr>
 * </table>
 */
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
	
	private final AddressService addressService;
	
	/**
	 * Constructor for Address controller. Creates the controller object by taking address service.
	 * @param addressService service class required to perform operations on address entity.
	 */
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}
	
	/**
	 * This Method Fetches all the address from DB.
	 * 
	 * @return a list of address from database.
	 */
	@GetMapping("/")
	public List<Address> getAllAddresss() {
		return addressService.getAllAddresss();
	}

	/**
	 * Fetches the information of Address whose ID is provided.
	 * @param addressId Id of the address whose info needs to be fetched.
	 * @return the Info of address that needs to be fetched.
	 */
	@GetMapping("/{addressId}")
	public Address getAddress(@PathVariable("addressId") long addressId) {
		return addressService.getAddress(addressId);
	}
	
	/**
	 * Updates the address with a new address provided in the body.
	 * @param address New Address
	 * @param addressId ID of the address that must be updated.
	 * @return updated address stored on DB
	 */
	@PutMapping("/{addressId}")
	public Address updateAddress(@Valid @RequestBody Address address, @PathVariable("addressId") long addressId) {
		return addressService.updateAddress(address, addressId);
	}

}
