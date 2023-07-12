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
	private String firstName;
	private String lastName; 
	
	@Column(columnDefinition = "DATE")
    private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
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
