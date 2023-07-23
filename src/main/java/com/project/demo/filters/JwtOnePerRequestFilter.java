package com.project.demo.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.demo.service.AuthorizationUserDetailsService;
import com.project.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtOnePerRequestFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;	
	private final AuthorizationUserDetailsService authUserDetailsService;
	

	public JwtOnePerRequestFilter(JwtUtil jwtUtil, AuthorizationUserDetailsService authUserDetailsService) {
		super();
		this.jwtUtil = jwtUtil;
		this.authUserDetailsService = authUserDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		String userName = null;
		String jwt = null;
		
		if(header != null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
			userName = jwtUtil.extractUserName(jwt);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.authUserDetailsService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken token = new 
						UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
