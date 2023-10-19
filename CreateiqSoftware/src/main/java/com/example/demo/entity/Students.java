package com.example.demo.entity;




import java.util.Date;
 

import org.springframework.validation.annotation.Validated;

import com.example.demo.exception.CoursesNotFoundException;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Validated
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sId;
	@Size(min = 2,max=11,message = "student name should have at atleast 2 characters")
	private String sName;
	@Size(min = 2,max=11,message = "father should have at atleast 2 characters")
	private String fathername;
	private Date dob;
	@Size(min = 2,message = " email should have at atleast 2 characters")
	private String email;
	@Size(min = 2,max=11,message = "education should have at atleast 2 characters")
	private String education;

//	@Size(min=7,message = "mobile number must be ten numbers")
	private Long mobileNo;
 	@Size(min = 2,max=11,message = "city should have at atleast 2 characters")
	private String city;
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name = "Course_id",nullable = true)
//	@JsonIgnore
	private Courses courses;
	
	 
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "batch_id", nullable = true)
//	@JsonIgnore
	private Batches batches;
	
	
}
