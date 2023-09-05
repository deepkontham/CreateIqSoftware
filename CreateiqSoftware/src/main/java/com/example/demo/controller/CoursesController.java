package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Courses;
import com.example.demo.service.CoursesServiceimpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/courses")
@CrossOrigin(origins = "http://localhost:4200/")
public class CoursesController {
	
	@Autowired
	private CoursesServiceimpl coursesServiceimpl;
	
	@PostMapping
//	@PreAuthorize("hasRole('ADMIN')")

	public ResponseEntity<Courses> createCourses(@Valid @RequestBody Courses courses){
		return new ResponseEntity<Courses>(coursesServiceimpl.createCourse(courses),HttpStatus.CREATED);
		
	}
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Courses> GetCourses(){
		 List<Courses> courses = coursesServiceimpl.GetCourses();
		return courses;
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("update/{coursesId}")
	public ResponseEntity<Courses> updateCourse( @Valid @PathVariable Long coursesId, Courses courses){
		return new ResponseEntity<Courses>(coursesServiceimpl.update(courses, coursesId),HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{coursesId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long coursesId){
		coursesServiceimpl.delete(coursesId);
		return new ResponseEntity<String>("Succesfully deleted",HttpStatus.OK);
		
	}
	@GetMapping("{coursesId}")
	public ResponseEntity<Courses> GetByid(Long coursesId){
		return new ResponseEntity<Courses>(coursesServiceimpl.getbyid(coursesId),HttpStatus.OK);
		
	}
	

}
