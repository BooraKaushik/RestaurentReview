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
import lombok.Data;

@Entity
@Table(name="Restaurent")
@Data
public class Restaurent {
	@Id
	@GeneratedValue
	@Column(name="Restaurent_ID")
	private long id;
	private String name;
	private String cusine;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "contact")
	private Contact contact;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "address")
	private Address address;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Restaurent_ID", referencedColumnName="Restaurent_ID")
	private Set<Rating> ratings = new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Restaurent_ID", referencedColumnName="Restaurent_ID")
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Restaurent_ID", referencedColumnName="Restaurent_ID")
	private Set<MenuItem> menu = new HashSet<>();
}
