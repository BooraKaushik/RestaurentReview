package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.dao.AddressDAO;
import com.project.demo.dao.RestaurantDAO;
import com.project.demo.dao.UserDAO;
import com.project.demo.model.Address;
import com.project.demo.model.Restaurant;
import com.project.demo.model.User;
import com.project.demo.serviceinterface.AddressService;

/**
 * This is a Service Class for Address providing CRUD operations.
 */
@Service
public class AddressServiceImpl implements AddressService {

	private final AddressDAO addressDao;
	private final RestaurantDAO restaurantDao;
	private final UserDAO userDao;

	/**
	 * Constructor for Address service with AddressDao as mandatory field.
	 * @param addressDao AddressRepo to perform CRUD operations.
	 */
    public AddressServiceImpl(AddressDAO addressDao, RestaurantDAO restaurantDao, UserDAO userDao) {
    	super();
        this.addressDao = addressDao;
        this.restaurantDao = restaurantDao;
        this.userDao = userDao;
    }

	@Override
	public Address addRestaurantAddress(Address address, long restaurantId) {
		Restaurant extractedRestaurant = restaurantDao.findById(restaurantId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Restaurant not found"
				));
		Address previousAddress = extractedRestaurant.getAddress();
		if(previousAddress != null) {
			addressDao.delete(previousAddress);
		}
		Address savedAddress = addressDao.save(address);
		extractedRestaurant.setAddress(savedAddress);
		restaurantDao.save(extractedRestaurant);
		return savedAddress;
	}
	
	@Override
	public Address addUserAddress(Address address, long userId) {
		User extractedUser = userDao.findById(userId).orElseThrow(()-> new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "User not found"
				));
		Address previousAddress = extractedUser.getAddress();
		if(previousAddress != null) {
			addressDao.delete(previousAddress);
		}
		Address savedAddress = addressDao.save(address);
		extractedUser.setAddress(savedAddress);
		userDao.save(extractedUser);
		return savedAddress;
	}

	@Override
	public List<Address> getAllAddresss() {
		return addressDao.findAll();
	}

	@Override
	public Address getAddress(long addressId) {
		return addressDao.findById(addressId).orElseThrow(()->new ResourceNotFoundException(String.format("No address found with id = %d", addressId)));
	}

	@Override
	public Address updateAddress(Address address, long addressId) {
		Address fetchedAddress = addressDao.findById(addressId).orElseThrow(()->new ResourceNotFoundException(String.format("No address found with id = %d", addressId)));
		if(address.getApt() != null) {
			fetchedAddress.setApt(address.getApt());
		}
		fetchedAddress.setStreet(address.getStreet());
		fetchedAddress.setCity(address.getCity());
		fetchedAddress.setState(address.getState());
		fetchedAddress.setCountry(address.getCountry());
		fetchedAddress.setPinCode(address.getPinCode());
		return addressDao.save(fetchedAddress);
	}

}
