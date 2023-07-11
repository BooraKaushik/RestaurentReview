package com.project.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	private String comment;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurent;

	@JsonIgnore
	@ManyToOne
	private User user;
	

}
