package com.project.demo.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.demo.model.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	@NotEmpty(message="Need Username to create a User")
	@Size(min = 4, message = "User Name should have minimum 4 characters")
	@Column(unique = true, nullable = false)
	private String userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message="Need Password to create a User")
	private String password;
	
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
	
	private ContactDTO contact;

	private AddressDTO address;
	
	private Set<RatingDTO> ratings;
	
	private Set<CommentDTO> comments;


}
