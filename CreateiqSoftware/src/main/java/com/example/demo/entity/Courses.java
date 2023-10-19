package com.example.demo.entity;


import java.util.HashSet;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long coursesId;
	@Size(min = 2,message = "Course name should have at atleast 2 characters")

	private String typeOfCourse;
//	@Size(min = 2,message = "typeofCourse should have at atleast 2 characters")

	private Double fees;
	@OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Students> students=new HashSet<>();
	


	

}
