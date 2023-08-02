package com.project.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDTO {
	
	@NotBlank(message="Comment cant be null")
	private String comment;

}
