package com.project.demo.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.dao.AddressDAO;
import com.project.demo.model.Address;
import com.project.demo.serviceinterface.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressDAO addressDao;

	/**
	 * Constructor for Address service with AddressDao as mandatory field.
	 * @param addressDao AddressRepo to perform CRUD operations.
	 */
    public AddressServiceImpl(AddressDAO addressDao) {
    	super();
        this.addressDao = addressDao;
    }

	@Override
	public Address addAddress(Address address) {
		return addressDao.save(address);
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

	@Override
	public void deleteAddress(long addressId) {
		addressDao.deleteById(addressId);
	}
    
}
