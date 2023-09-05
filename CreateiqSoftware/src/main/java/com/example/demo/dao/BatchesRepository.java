package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Batches;
import com.example.demo.entity.Students;

public interface BatchesRepository  extends JpaRepository<Batches, Long>{
//	@Query("Select s from Batches b join fetch Students s on b.batchid=s.sId where sId=?1")

//	int findBystudents(Long sid);
	
	Batches findBybName(String BName);



}
