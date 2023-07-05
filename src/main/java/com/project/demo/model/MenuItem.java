package com.project.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

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
}
