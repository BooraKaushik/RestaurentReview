package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="address")
@Builder
@Data
public class Address {
	
	@Id
	@GeneratedValue
	private long id;
	private String street;
	private String apt;
	private String city;
	private String state;
	private String country;
	private int pinCode;

}
