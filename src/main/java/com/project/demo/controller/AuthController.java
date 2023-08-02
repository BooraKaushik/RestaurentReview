package com.project.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.AuthRequest;
import com.project.demo.dto.AuthResponse;
import com.project.demo.service.AuthorizationUserDetailsService;
import com.project.demo.serviceinterface.UserService;
import com.project.demo.util.JwtUtil;

import jakarta.validation.Valid;

/**
 * @author Kaushik Boora.
 * Authentication Controller allows users to login.
 * The API path for Address is /api/v1/address.
 * <table border="1">
 * <caption>API description</caption>
 * <tr><th>HTTP Method</th><th>Path</th><th>Description</th></tr>
 * <tr><td>POST</td><td>/login/</td>
 * <td>Allows users to login,by taking in userName and password. 
 * If user is authenticated JWT along with the user object. 
 * Else a null is returned in JWT's Place.</td></tr>
 * </table>
 */
@RestController
@RequestMapping("/login")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final AuthorizationUserDetailsService authUserDetailsService;
	private final JwtUtil jwtUtil;
	private final UserService userService;

	/**
	 * Constructor for AuthController to allow users and generate JWT.
	 * @param authenticationManager to authenticate user.
	 * @param jwtUtil to generate jwt.
	 * @param authUserDetailsService to send user Details.
	 * @param userService to fetch and send user Details.
	 */
	public AuthController(
			AuthenticationManager authenticationManager, 
			JwtUtil jwtUtil, 
			AuthorizationUserDetailsService authUserDetailsService,
			UserService userService
			) {
		super();
		this.authenticationManager = authenticationManager;
		this.authUserDetailsService = authUserDetailsService;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
	
	/**
	 * Login request, expects userName and password to be present in the body.
	 * @param request body containing userName and password.
	 * @return Jwt along with User Entity.
	 */
	@PostMapping("/")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
					);
		} catch(Exception except) {
			return new ResponseEntity<>(new AuthResponse(), HttpStatus.UNAUTHORIZED);
		}

		AuthResponse response = new AuthResponse();
		String userName = request.getUserName();
		response.setUser(userService.getUserByUserName(userName));		
		UserDetails userDetails = authUserDetailsService.loadUserByUsername(userName);
		String jwt = jwtUtil.generateToken(userDetails);
		response.setJwt(jwt);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
