package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.dao.BatchesRepository;
import com.example.demo.entity.Batches;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Students;
import com.example.demo.service.BatchesServiceImpl;
import com.example.demo.service.CoursesServiceimpl;
import com.example.demo.service.StudentServiceImpl;

@SpringBootApplication
@EnableTransactionManagement
public class CreateiqSoftwareApplication implements CommandLineRunner {
	
//	@Autowired
//	private BatchesServiceImpl batchesServiceImpl;
	
//	private CoursesServiceimpl coursesServiceimpl;

	@Autowired
	private StudentServiceImpl impl;

	public static void main(String[] args) {
		SpringApplication.run(CreateiqSoftwareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		List<Students> getstudents = impl.Getstudents();
//       System.out.println(getstudents);
//		
		
//		Students getbyid = impl.getbyid(455l);
//		System.out.println(getbyid);
       
//       List<Students> studentsByBatch = impl.getStudentsByBatch("march");
//       System.out.println(studentsByBatch );

//      impl.delete(455l);

//		Courses c=new Courses();
//		
//		coursesServiceimpl.update(null, null)
		
//		List<Students> getbyname = impl.getbyname("sai");
//		System.out.println(getbyname);
//		
//		Students s=new Students();
//		s.setEduction("Mechanical"); 
//		
			
		}
		
	}


