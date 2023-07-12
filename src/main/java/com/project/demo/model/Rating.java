package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This Class represents the Rating entity on DB.
 */
@Entity
@Table(name="Rating")
@Data
public class Rating {
	@Id
	@GeneratedValue
	private long id;
	private int value;


	@JsonIgnore
	@ManyToOne
	private Restaurant restaurent;

	@JsonIgnore
	@ManyToOne
	private User user;
}
