package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> {
	
	
	 Courses findByTypeOfCourse(String typeOfCourse);


}
