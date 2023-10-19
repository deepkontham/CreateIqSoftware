package com.example.demo.config;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity

//for swagger security
@SecurityScheme(
		name="Bear Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme="bearer"
		
		)
public class SecurityConfig {

	private UserDetailsService detailsService;
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;
	
	
	
	
	public SecurityConfig(UserDetailsService detailsService, JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		super();
		this.detailsService = detailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}

	@Bean
	 protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) -> 
//		authorize.anyRequest().authenticated())
		//here we want access only get api for all users
		authorize.requestMatchers(HttpMethod.GET,"/api/v1/**").permitAll().requestMatchers("/api/v1/auth/**").permitAll().requestMatchers("/swagger-ui/**").permitAll().requestMatchers("/v3/api-docs/**").permitAll()
		. anyRequest().authenticated()).exceptionHandling(exception ->exception
				.authenticationEntryPoint(authenticationEntryPoint)).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//				.httpBasic(Customizer.withDefaults());
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	// No of Users@password are allocated here
	//while using Database Authentication no need Inmemory Objects
//	@Bean
//	public UserDetailsService detailsService() {
//		UserDetails pradeep = User.builder().username("pradeep").password(passwordEncoder().encode("deep"))
//				.roles("User").build();
//
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(pradeep, admin);
//
//	}

}
