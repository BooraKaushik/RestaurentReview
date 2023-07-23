package com.project.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This Class represents the response sent to User upon authentication.
 */
@Getter
@Setter
public class AuthResponse {
	
	private String jwt;
	private User User;
}
