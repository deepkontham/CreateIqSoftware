package com.example.demo.controller;

import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	//in order to support both url use like this
	// Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
 //Build Rest Api
 @PostMapping(value = {"/register","/signup"})
 public ResponseEntity<String> register(@RequestBody RegisterDto dto){
	 String register = authService.register(dto);
	 return new ResponseEntity<>(register,HttpStatus.CREATED);
	 
 }
	
	
	

}
