package com.project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.demo.filters.JwtOnePerRequestFilter;

/**
 * Configuration class to configure allowed routes, password encoder and authManager.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private final JwtOnePerRequestFilter jwtAuthenticationFilter;
	
	/**
	 * Constructor for SecurityConfiguration Class, takes in jwtAuthFilter 
	 * to execute the filter before every request.
	 * @param jwtAuthenticationFilter a OncePerRequest filter to be executed before every request.
	 */
	public SecurityConfiguration(@Lazy JwtOnePerRequestFilter jwtAuthenticationFilter) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	/**
	 * This method configures Http by adding authentication 
	 * to all the routes except for "/login" and "/api/v1/user/".
	 * @param http HttpSecurity object.
	 * @return security filter chain by building http.
	 * @throws Exception http might raise exception.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/user/*", "/login/*")
                .permitAll()
                .anyRequest().authenticated())
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	    return http.build();
	}

	/**
	 * Password Encoder bean. 
	 * @return Bcrypt password encoder.
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * This bean fetches authentication manager and returns it.
	 * @param authenticationConfiguration config to fetch authManager.
	 * @return authentication Manger.
	 * @throws Exception this  method may throw unauthorized exception and needs to be handled.
	 */
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration
			) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
