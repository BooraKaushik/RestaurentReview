package com.project.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Table(name="Rating")
@Data
public class Rating {
	@Id
	@GeneratedValue
	private long id;
	private int value;

}
