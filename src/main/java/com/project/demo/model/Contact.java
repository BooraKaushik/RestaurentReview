package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private String phoneNumber;
	private String email;
}
