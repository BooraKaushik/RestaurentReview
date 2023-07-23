package com.project.demo.service;

import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.demo.model.User;
import com.project.demo.serviceinterface.UserService;

/**
 * AuthorizationUserDetailsService provides user details services for Authorization. 
 */
@Service
public class AuthorizationUserDetailsService implements UserDetailsService {
	
	private final UserService userService;
	
	/**
	 * Constructor for AuthorizationUserDetailsService.
	 * @param userService user service to fetch user details.
	 */
	public AuthorizationUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userExtracted = this.userService.getUserByUserName(username);
		return new org.springframework.security.core.userdetails.User(
						userExtracted.getUserName(), 
						userExtracted.getPassword(), 
						new HashSet<GrantedAuthority>()
						);
	}

}
