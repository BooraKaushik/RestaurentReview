package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * This class represents Contact entity on Database.
 */
@Entity
@Table(name = "Contact")
@Data
public class Contact {
	@Id
	@GeneratedValue
	private long id;

	@Pattern(regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$", message = "Invalid phone number")
	private String phoneNumber;
	
	@Pattern(regexp = "^[\\w.-]+@[a-zA-Z_-]+?(?:\\.[a-zA-Z]{2,})+$", message = "Invalid email address")
	private String email;
}
