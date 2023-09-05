package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

import com.example.demo.entity.Batches;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Students;
import com.example.demo.service.StudentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {
	
	@Autowired
	private StudentServiceImpl impl;
	
//	@PreAuthorize("hasRole('ADMIN')")
 	@PostMapping("{coursename}/{bname}/students")
	public ResponseEntity<Students> createStudent( @PathVariable String coursename, @PathVariable String bname,@Valid @RequestBody Students students){
		return new ResponseEntity<Students>(impl.createpost(coursename, bname, students) ,HttpStatus.CREATED);
		
	}
 	@PostMapping("save/students")
 	public ResponseEntity<Students> createStudent(@Valid @RequestBody Students students){
 		return new ResponseEntity<Students>(impl.createpost1(students) ,HttpStatus.CREATED);
 		
 	}
	
//	@GetMapping("user")
//	public String index() {
//		return "index";
//	}
//	
	@GetMapping("students")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Students> GetStudent(){
		 List<Students> getstudents = impl.Getstudents();
		 
		return getstudents;
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")

	@PutMapping("update/{sId}")
	public ResponseEntity<Students> updateStudent(@Valid @PathVariable Long sId,@Valid @RequestBody Students dto){
		return new ResponseEntity<Students>(impl.update(dto, sId),HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN')")

	@DeleteMapping("delete/{sId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long sId){
		impl.delete(sId);
		return new ResponseEntity<String>("Succesfully deleted",HttpStatus.OK);
		
	}
	@GetMapping("getbyid/{sId}")
	public ResponseEntity<Students> GetByid(@PathVariable Long sId){
		return new ResponseEntity<Students>(impl.getbyid(sId),HttpStatus.OK);
		
	}
	@GetMapping("getbyname/{sName}")
	public List<Students> GetBysname(@PathVariable String sName){
		List<Students> getbyname = impl.getbyname(sName);
		return getbyname;
		
	}
	@GetMapping("batches/{bName}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Students> GetStudByBatchname(@PathVariable String bName){
		 List<Students> getstudents = impl.getStudentsByBatch(bName);
		return getstudents;
		
	}
	@GetMapping("students/{batchid}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Students> GetStudByBatchid(@PathVariable Long batchid){
		 List<Students> getstudents = impl.getStudentsByBatchid(batchid);
		return getstudents;
		
	}

	@GetMapping("Courses/{coursename}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Students> GetStudByCourseid(@PathVariable String coursename){
		 List<Students> getstudents = impl.getStudentsByCoursename(coursename);
		return getstudents;
		
	}

	
	
	
	
	

}
