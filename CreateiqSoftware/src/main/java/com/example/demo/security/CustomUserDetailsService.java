package com.example.demo.security;

import java.util.Optional;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	

	@Override
	public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
		
		 User user = repository.findByUsernameOrEmail(usernameoremail, usernameoremail).orElseThrow(()->new UsernameNotFoundException("user not found with this username or email"+usernameoremail ));
		 
		 Set<GrantedAuthority> authorities=user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		 return new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getPassword(),authorities);
		 }
}
