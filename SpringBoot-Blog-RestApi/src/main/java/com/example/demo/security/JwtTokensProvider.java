package com.example.demo.security;

import java.security.Key;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.exception.BlogApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokensProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;
	
	
	//generate JWT token
	public String  generateToken(Authentication authentication) {
		String username=authentication.getName();
		Date currentdate=new Date();
		Date expireDate=new Date(currentdate.getTime()+jwtExpirationDate);
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(expireDate).setExpiration(expireDate).signWith(key()).compact();
		return token;
		
	
	}
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	//get username From jwt token
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
	String username =claims.getSubject();
	return username;
	
	}
	//validate jwt token
	public  boolean validateToken(String token) {
		try {
		Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
		return true;
		
	}catch (MalformedJwtException e) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		
	}catch (ExpiredJwtException e) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
	}catch (UnsupportedJwtException e) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
	}catch (IllegalArgumentException e) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, " JWT claims String is  empty");
		
	}
	
}
}


