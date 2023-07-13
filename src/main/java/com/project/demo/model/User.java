package com.project.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="User_ID")
	private long id;
	
	@NotBlank(message="First Name of User cant be null")
	private String firstName;
	
	@NotBlank(message="Last Name of User cant be null")
	private String lastName; 
	
	@Column(columnDefinition = "DATE")
	@Past(message="DOB of User cant be in Future")
    private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Geneder of User should be MALE / FEMALE / OTHER")
	private Gender gender;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contact")
	private Contact contact;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "address")
	private Address address;
	

	@OneToMany(mappedBy="user", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<Rating> ratings = new HashSet<>();
	
	@OneToMany(mappedBy="user", orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

    
}
