package com.harith.blog.service;

import java.util.List;

import com.harith.blog.payload.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	List<CategoryDto> getCategories();
	
	CategoryDto getCategory(Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	
}
