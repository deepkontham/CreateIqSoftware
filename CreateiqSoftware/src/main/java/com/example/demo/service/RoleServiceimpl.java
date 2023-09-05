package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Role;

@Service
public class RoleServiceimpl implements RoleService {
	
	
	@Autowired
	RoleRepository repository;

	@Override
	public Role createrole(Role role) {
		return repository.save(role);
	}

}
