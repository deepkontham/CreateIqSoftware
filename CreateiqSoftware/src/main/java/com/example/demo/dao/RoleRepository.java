package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByname(String name);
	

}
