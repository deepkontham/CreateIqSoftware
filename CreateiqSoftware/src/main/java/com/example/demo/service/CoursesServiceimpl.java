package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CoursesRepository;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Students;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class CoursesServiceimpl implements CoursesService {

	@Autowired
	private CoursesRepository coursesRepository;

	
	@Override
	public Courses createCourse(Courses dto) {
		return coursesRepository.save(dto);
	}



	@Override
	public Courses getbyid(Long coursesId) {
		return coursesRepository.findById(coursesId).get();
	}


	@Override
	public Courses update(Courses dto, Long coursesId) {
		return coursesRepository.save(dto);
	}

	@Override
	public void delete(Long coursesId) {
		coursesRepository.deleteById(coursesId);
	}



	@Override
	public List<Courses> GetCourses() {
		return coursesRepository.findAll();

	}

}
