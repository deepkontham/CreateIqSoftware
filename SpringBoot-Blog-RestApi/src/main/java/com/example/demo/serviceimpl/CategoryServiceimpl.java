package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CategoryDto;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper modelmapper;

	public CategoryServiceimpl(CategoryRepository categoryRepository, ModelMapper modelmapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelmapper = modelmapper;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelmapper.map(categoryDto, Category.class);
		Category savecategory = categoryRepository.save(category);
		return modelmapper.map(savecategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryByid(Long categoryid) {
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryid));
		return modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> categoryDtos() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map((category) -> modelmapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto updatecategoryDto(CategoryDto categoryDto, Long categoryid) {
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryid));
		
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		Category upadatecategory = categoryRepository.save(category);
		
		return modelmapper.map(upadatecategory, CategoryDto.class) ;
	}

	@Override
	public void deletcategory(Long categoryid) {
		
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryid));
		
		categoryRepository.delete(category);
	}

}
