package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * This Class represents the Comment entity on DB.
 */
@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotBlank(message="Comment cant be null")
	private String comment;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurent;

	@JsonIgnore
	@ManyToOne
	private User user;
	

}
