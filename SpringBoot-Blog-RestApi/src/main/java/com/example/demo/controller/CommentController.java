package com.example.demo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.CommentDto;
import com.example.demo.service.CommentServie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1 ")
public class CommentController {

	private CommentServie comServie;

	public CommentController(CommentServie comServie) {
		this.comServie = comServie;
	}

	@PostMapping("/posts/{postid}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postid") Long postid,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(comServie.createcomment(postid, commentDto), HttpStatus.CREATED);

	}

//	@GetMapping("/post/{postid}/comments")
//	public List<CommentDto> getcommentsbypostid(@PathVariable(value = "postid") long postid){
//		return comServie.getcommentsbypostid(postid);
//		
//	}
	@GetMapping("/posts/{postid}/comments/{id}")
	public ResponseEntity<CommentDto> getcommentbyid(@PathVariable(value = "postid") Long postid,
			@PathVariable(value = "id") Long commentid) {
		CommentDto commentDto = comServie.getcommentbyid(postid, commentid);

		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);

	}

	@PutMapping("/posts/{postid}/comments/{id}")
	public ResponseEntity<CommentDto> upadtecommentbyid(@PathVariable(value = "postid") Long postid,
			@PathVariable(value = "id") Long commentid,@Valid   @RequestBody CommentDto commentDto) {

		CommentDto updatecommentbyid = comServie.updatecommentbyid(postid, commentid, commentDto);
		return new ResponseEntity<CommentDto>(updatecommentbyid, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postid}/comments/{id}")
	public ResponseEntity<String> deletecommentbyid(@PathVariable(value = "postid") Long postid,
			@PathVariable(value = "id") Long commentid) {

		comServie.Deletecommentbyid(postid, commentid);
		return new ResponseEntity<>("delete successfully", HttpStatus.OK);

	}

}
