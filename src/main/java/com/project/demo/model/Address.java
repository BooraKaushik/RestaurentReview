package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * This Class represents the Address entity on DB.
 */
@Entity
@Table(name="address")
@Data
public class Address {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotBlank(message="Street Cant be null")
	private String street;
	private String apt;

	@NotBlank(message="City Cant be null")
	private String city;

	@NotBlank(message="State Cant be null")
	private String state;

	@NotBlank(message="Country Cant be null")
	private String country;
	
	@Digits(integer = 6, fraction = 0, message = "PIN code must be a 6-digit number")
    @Min(value = 100000, message = "PIN code must be at least 100000")
    @Max(value = 999999, message = "PIN code cannot exceed 999999")
    private String pinCode;

}
