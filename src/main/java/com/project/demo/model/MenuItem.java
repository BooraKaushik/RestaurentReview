package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * This Class represents the MenuItem entity on DB.
 */
@Entity
@Data
public class MenuItem {
	@Id
	@GeneratedValue
	@Column(name="MenuItem_ID")
	private long id;
	private float price;
	private float calories;
	
	@OneToOne
	@JoinColumn(name="Food")
	private Food food;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
}
