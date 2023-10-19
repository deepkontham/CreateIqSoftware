package com.example.demo.service;

import java.util.List;

import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;

public interface PostService {
	
	PostDto createpost(PostDto dto) ;	
	PostResponse Getposts(int pageNo,int pageSize,String Sortby,String SortDir);
	
	PostDto getbyid(Long id);
	PostDto updatepost( PostDto dto, Long id);
	void deletepost(Long id);
	List<PostDto> getPostsByCategory(Long categoryid);
	}


