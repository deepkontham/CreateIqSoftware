package com.example.demo.service;

import java.util.List;

import com.example.demo.payload.CategoryDto;

public interface CategoryService {
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto getCategoryByid(Long categoryid);
	
	List<CategoryDto> categoryDtos();
	
	CategoryDto updatecategoryDto(CategoryDto categoryDto,Long categoryid);
	 
	void deletcategory(Long categoryid);

}
