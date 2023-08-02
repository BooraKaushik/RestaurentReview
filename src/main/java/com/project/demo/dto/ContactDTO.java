package com.project.demo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

	@Pattern(regexp = "^(\\+\\d{1,3})?[-.\\s]?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$", message = "Invalid phone number")
	private String phoneNumber;
	
	@Pattern(regexp = "^[\\w.-]+@[a-zA-Z_-]+?(?:\\.[a-zA-Z]{2,})+$", message = "Invalid email address")
	private String email;

}
