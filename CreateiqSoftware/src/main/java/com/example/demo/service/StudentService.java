package com.example.demo.service;

import java.util.List;



import com.example.demo.entity.Students;


public interface StudentService {


	Students createpost(String Coursename, String bname,Students dto) ;	
	Students createpost1(Students dto) ;	
	 List<Students> Getstudents();
	
	Students getbyid(Long sId);
	Students update( Students dto, Long sId);
	void delete(Long sId);
	List<Students> getStudentsByBatch(String bName);
	List<Students> getbyname(String sname);
    List<Students> getStudentsByBatchid(Long batchid);
    List<Students> getStudentsByCoursename(String Coursename);


	
}
