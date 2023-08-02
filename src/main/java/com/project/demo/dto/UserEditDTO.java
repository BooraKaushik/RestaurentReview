package com.project.demo.dto;

import java.time.LocalDate;

import com.project.demo.model.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEditDTO {
	
	private String firstName;
	
	private String lastName; 
	
	@Column(columnDefinition = "DATE")
    private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;


}
