package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.Batches;

public interface BatchesServices {

	Batches createbranch(Batches batches);

	List<Batches> Getbatches();

	Batches getbyid(Long batchid);

	Batches update(Batches dto, Long id);

	void delete(Long id);
//	int getBatchByStudentid(Long sid);
//	List<Students> getStudentsByBatch(String bName);


}
