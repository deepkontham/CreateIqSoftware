package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.JWTAuthResponse;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("login")
	public ResponseEntity<JWTAuthResponse> Login(@RequestBody LoginDto dto){
		String login = authService.login(dto);
		JWTAuthResponse authResponse=new JWTAuthResponse();
		authResponse.setAccessToken(login);
		return  ResponseEntity.ok(authResponse);
		 
	}
	@PostMapping("register")
	public ResponseEntity<String> Register(@RequestBody RegisterDto dto){
		String register = authService.register(dto);
		return  ResponseEntity.ok(register);
	}
	
	
	

}
