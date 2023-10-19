package com.example.demo.service;



import com.example.demo.payload.CommentDto;

public interface CommentServie {
	CommentDto createcomment(Long postid, CommentDto commentDto);
//	List<CommentDto> getcommentsbypostid(long postid);
	CommentDto getcommentbyid(Long postid, Long commentid);
	
	CommentDto updatecommentbyid(Long postid,Long commentid,CommentDto commentDto);
	void Deletecommentbyid(Long postid ,Long commentid); 
	
	

}
