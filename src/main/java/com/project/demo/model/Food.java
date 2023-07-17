package com.project.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This Class represents the Food entity on DB.
 */
@Entity
@Data
public class Food {
	@Id
	@GeneratedValue
	@Column(name="Food_Id")
	private long id;
	private String name;
	private String ingredients;
	private String cusine;

}
