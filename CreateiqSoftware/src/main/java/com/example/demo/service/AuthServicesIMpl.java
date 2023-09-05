package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.BlogApiException;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.security.JwtTokensProvider;

@Service
public class AuthServicesIMpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository rolerepository;
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokensProvider jwtTokensProvider;

	@Override
	public String login(LoginDto loginDto) {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtTokensProvider.generateToken(authenticate);
		
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Userame Alredy Exists");
		}
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email Alredy Exists");
		}
		User user=new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(encoder.encode(registerDto.getPassword()));
		Set<Role> s=new HashSet<>();
		Role role = rolerepository.findByname("ROLE_USER").get();
		s.add(role);
		user.setRoles(s);
		userRepository.save(user);
		
		return "User registered Successfully";
	}

}
