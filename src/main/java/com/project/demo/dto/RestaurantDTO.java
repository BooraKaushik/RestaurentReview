package com.project.demo.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
	
	@NotBlank(message="Name of the restaurant is required")
	private String name;
	private String cusine;
	
	private ContactDTO contact;

	private AddressDTO address;
	
	private Set<RatingDTO> ratings;
	
	private Set<CommentDTO> comments;

	private Set<MenuItemDTO> menu;

}
