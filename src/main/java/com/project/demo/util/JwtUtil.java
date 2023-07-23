package com.project.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtUtil class provides operations to create JWT.
 */
@Service
public class JwtUtil {
	
	private final String KEY = "secretsecretsecretsecretsecretsecretsecretsecr";

	/**
	 * Fetches UserName from JWT
	 * @param token JWT token
	 * @return User Name.
	 */
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	/**
	 * Fetches claims of a JWT
	 * @param <T> type of claim
	 * @param tokenJWT token.
	 * @param claimResolver function to extract claim.
	 * @return claim that need to be fetched.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.setSigningKey(KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
	/**
	 * Fetches expiration time from JWT
	 * @param token JWT token
	 * @return Expiration time.
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	/**
	 * Creates JWT Token from user details.
	 * @param userDetails userdetails to generate JWT.
	 * @return JSON Web Token.
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	/**
	 * Creates JWT by setting claims and subject.
	 * @param claims claims of JWT.
	 * @param subject subject of JWT.
	 * @return a newly created JWT.
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 * 12))
				.signWith(SignatureAlgorithm.HS256, KEY)
				.compact();
	}
	
	/**
	 * Validates if a JWT token is valid or not by checking the userName and expiration time.
	 * @param token Jwt Token that needs to be validated.
	 * @param userDetails UserDetails to validate JWT with.
	 * @return true if userDetails match else returns false.
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExpired(token) ;
	}

}
