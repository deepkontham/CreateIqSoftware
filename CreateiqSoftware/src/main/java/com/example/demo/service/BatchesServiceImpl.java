package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BatchesRepository;
import com.example.demo.entity.Batches;
import com.example.demo.entity.Students;

import jakarta.transaction.Transactional;


@Service


public class BatchesServiceImpl implements BatchesServices {

	@Autowired
	private BatchesRepository batchesRepository;
	

	@Override
	@Transactional()
	public Batches createbranch(Batches batches) {
		return batchesRepository.save(batches);
	}

	@Override
	public List<Batches> Getbatches() {
		return batchesRepository.findAll();
	}

	@Override
	public Batches update(Batches dto, Long id) {
		return batchesRepository.save(dto);
	}

	@Override
	public Batches getbyid(Long batchid) {
		return batchesRepository.findById(batchid).get();
	}

	@Override
	public void delete(Long id) {
		batchesRepository.existsById(id);
	}
//
//	@Override
//	public int getBatchByStudentid(Long sid) {
//		return batchesRepository.findBystudents(sid);
//	}

//	@Override
//	public List<Students> getStudentsByBatch(String bName) {
//		return batchesRepository.findBybName(bName);
//	}


}
