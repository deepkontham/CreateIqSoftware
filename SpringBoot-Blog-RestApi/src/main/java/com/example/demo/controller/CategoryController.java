package com.example.demo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.CategoryDto;
import com.example.demo.payload.PostDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PostService;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	private CategoryService categoryService;
	private PostService postService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addcategory(@RequestBody CategoryDto categoryDto){
		CategoryDto addCategory = categoryService.addCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(addCategory,HttpStatus.CREATED);
		
	}
	
     @GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategorty(@PathVariable("id") Long categoryid){
		CategoryDto categoryByid = categoryService.getCategoryByid(categoryid);
		return  ResponseEntity.ok(categoryByid);
	}
     @GetMapping
     public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(categoryService.categoryDtos());
    	 
     }
     @PutMapping("{id}")
     @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<CategoryDto> updateCatogory(@RequestBody CategoryDto categoryDto,@PathVariable("id") Long categoryid){
    	 
		return ResponseEntity.ok(categoryService.updatecategoryDto(categoryDto, categoryid));
    	 
     }
     @DeleteMapping("{id}")
     @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryid){
      categoryService.deletcategory(categoryid);
		
		return  ResponseEntity.ok("delete succesfully");
	}
     @GetMapping("/category/{id}")
     public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryid){
	List<PostDto> postsdteos = postService.getPostsByCategory(categoryid);
    	 return ResponseEntity.ok(postsdteos);
    	 
     }
     
}
