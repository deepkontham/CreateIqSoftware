package com.example.demo.service;

import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	
    String register(RegisterDto registerDto);
}
