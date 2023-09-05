package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleServiceimpl;

@RestController
@RequestMapping("api/")
public class RoleController {
	
	@Autowired
	private RoleServiceimpl roleServiceimpl;
	
	@PostMapping("roles")
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		return new ResponseEntity<Role>(roleServiceimpl.createrole(role),HttpStatus.CREATED);
	}

}
