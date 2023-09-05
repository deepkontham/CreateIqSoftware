package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Courses;
import com.example.demo.entity.Students;

public interface CoursesService {

	Courses createCourse( Courses dto);

	public List<Courses> GetCourses();

	Courses getbyid(Long coursesId);


	Courses update(Courses dto, Long coursesId);

	void delete(Long coursesId);
	
	

}
