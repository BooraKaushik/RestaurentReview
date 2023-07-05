package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Comment")
@Builder
@Data
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="Restaurent_ID")
	private Restaurent restaurent;
	
	@ManyToOne
	@JoinColumn(name="User_ID")
	private User user;

}
