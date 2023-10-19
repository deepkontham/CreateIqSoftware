package com.example.demo.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.BlogApiException;
import com.example.demo.payload.LoginDto;
import com.example.demo.payload.RegisterDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokensProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceimpl implements AuthService {

	
	private AuthenticationManager authenticationManager;
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokensProvider jwtTokensProvider;
	
	public AuthServiceimpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokensProvider jwtTokensProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokensProvider = jwtTokensProvider;
	}

	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		 
		SecurityContextHolder.getContext().setAuthentication(authentication);;
		
		String token=jwtTokensProvider.generateToken(authentication);
		
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) {
		//add check for username exists in database
		if(userRepository.existsByUsername(registerDto.getUsername())) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exists");
	}
		//add check for email exists in database

		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is already exists");
		}
		User user =new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles =new HashSet<>();
		Role role=roleRepository.findByname("ROLE_USER").get();
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		return "User registered Successfully";


}
}
