package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Batches {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long batchid;
	@Size(min = 2,max=11,message = "batch name should have at atleast 2 characters")

	private String bName;
	
	@OneToMany(mappedBy = "batches", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Students> students;

	
}
