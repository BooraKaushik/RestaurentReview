package com.project.demo.serviceinterface;

import java.util.List;

import com.project.demo.model.Address;

/**
 * This Interface provides CRUD functionalities for Rating.
 */
public interface AddressService {
	
	/**
	 * Adds address to the DB.
	 * @param address Address data sent as a part of body.
	 * @return the address that is created on the database.
	 */
	public Address addAddress(Address address);
	
	/**
	 * This Method Fetches all the addresses from DB.
	 * 
	 * @return a list of addresses from database.
	 */
	public List<Address> getAllAddresss();
	
	/**
	 * Fetches the information of Address whose ID is provided.
	 * @param addressId Id of the address whose info needs to be fetched.
	 * @return the Info of address that needs to be fetched.
	 */
	public Address getAddress(long addressId);
	
	/**
	 * Updates the address with a new address provided in the body.
	 * @param address New Address
	 * @param addressId ID of the address that must be updated.
	 * @return updated address stored on DB
	 */
	public Address updateAddress(Address address, long addressId);
	
	/**
	 * Deletes a address whose id is provided. Make sure that reference to the associated entity is deleted first.
	 * @param addressId ID of the address that must be deleted.
	 */
	public void deleteAddress(long addressId);

}
