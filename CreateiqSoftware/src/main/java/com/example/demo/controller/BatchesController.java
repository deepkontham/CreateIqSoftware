package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.service.BatchesServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/Batches")
@CrossOrigin(origins = "http://localhost:4200/")
public class BatchesController {

	@Autowired
	private BatchesServiceImpl batchesServiceImpl;

	
//	@PreAuthorize("hasRole('ADMIN')")

	@PostMapping
	public ResponseEntity<Batches> createBatches(@Valid @RequestBody Batches batches) {
		return new ResponseEntity<Batches>(batchesServiceImpl.createbranch(batches), HttpStatus.CREATED);

	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Batches> GetBatches() {
		List<Batches> batches = batchesServiceImpl.Getbatches();
		return batches;

	}
	@PreAuthorize("hasRole('ADMIN')")

	@PutMapping("{batchid}")
	public ResponseEntity<Batches> updateBatches(@Valid @PathVariable Long batchid, Batches batches) {
		return new ResponseEntity<Batches>(batchesServiceImpl.update(batches, batchid), HttpStatus.OK);

	}
//	@PreAuthorize("hasRole('Admin')")

	@DeleteMapping("{batchid}")
	public ResponseEntity<String> deleteBatches(@PathVariable Long batchid) {
		batchesServiceImpl.delete(batchid);
		return new ResponseEntity<String>("Succesfully deleted", HttpStatus.OK);

	}

	@GetMapping("{batchid}")
	public ResponseEntity<Batches> GetByid(@PathVariable Long batchid) {
		return new ResponseEntity<Batches>(batchesServiceImpl.getbyid(batchid), HttpStatus.OK);

	}
	

}
