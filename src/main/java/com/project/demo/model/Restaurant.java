package com.project.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Restaurent")
@Getter
@Setter
public class Restaurant {
	@Id
	@GeneratedValue
	@Column(name="Restaurent_ID")
	private long id;
	
	@NotBlank(message="Name of the restaurant is required")
	private String name;
	private String cusine;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "contact")
	private Contact contact;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "address")
	private Address address;
	
	@OneToMany(mappedBy="restaurant", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<Rating> ratings = new HashSet<>();
	
	@OneToMany(mappedBy="restaurant", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

	@OneToMany(mappedBy="restaurant", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<MenuItem> menu = new HashSet<>();
}
