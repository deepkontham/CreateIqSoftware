package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BatchesRepository;
import com.example.demo.dao.CoursesRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Batches;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Students;
import com.example.demo.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;
	@Autowired
	private CoursesRepository coursesRepository;
	@Autowired
	private BatchesRepository batchesRepository;

	@Override
	public List<Students> Getstudents() {
		return repository.findAll();
	}

	@Override
	public Students getbyid(Long sId) {

		Students s = repository.findById(sId).orElseThrow(() -> new ResourceNotFoundException("students", "sId", sId));
		return s;

	}

	@Override
	public Students update(Students dto, Long sId) {
		Students s = repository.findById(sId).orElseThrow(() -> new ResourceNotFoundException("students", "sId", sId));
		s.setSId(dto.getSId());
		s.setSName(dto.getSName());
		s.setFathername(dto.getFathername());
		s.setDob(dto.getDob());
		s.setEmail(dto.getEmail());
		s.setMobileNo(dto.getMobileNo());
		s.setCity(dto.getCity());
		s.setEducation(dto.getEducation());
		s.setBatches(dto.getBatches());
		s.setCourses(dto.getCourses());
		return repository.save(s);
	}

	@Override
	public void delete(Long sId) {
		repository.deleteById(sId);

	}

	@Override
	public List<Students> getbyname(String sname) {
		return repository.findBysName(sname);
	}

	@Override
	public List<Students> getStudentsByBatch(String bName) {
		return repository.findByBname12(bName);
	}

	@Override
	public List<Students> getStudentsByBatchid(Long batchid) {
		return repository.findByBatchid(batchid);
	}

	@Override
	public List<Students> getStudentsByCoursename(String Coursename) {
		return repository.findByCouresesName(Coursename);
	}

	@Override
	public Students createpost(String Coursename, String bname, Students dto) {

//		Batches batches = dto.getBatches();
		Courses courses = coursesRepository.findByTypeOfCourse(Coursename);
		System.out.println(courses);
		Batches batch = batchesRepository.findBybName(bname);
		System.out.println(batch);
		dto.setCourses(courses);
		dto.setBatches(batch);
		Students save = repository.save(dto);
		return save;

	}

	@Override
	public Students createpost1(Students dto) {
		Courses courses = coursesRepository.findById(dto.getCourses().getCoursesId()).get();
		System.out.println(courses);
		Batches batch = batchesRepository.findById(dto.getBatches().getBatchid()).get();
		System.out.println(batch);
		dto.setCourses(courses);
		dto.setBatches(batch);
		Students save = repository.save(dto);
		return save;
	}

}
