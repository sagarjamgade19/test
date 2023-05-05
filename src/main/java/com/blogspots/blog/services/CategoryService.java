package com.blogspots.blog.services;

import java.util.List;

import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) throws ResourceNotFoundException;
	void deleteCategory(Integer categoryId) throws ResourceNotFoundException;
	CategoryDto getCategory(Integer categoryId) throws ResourceNotFoundException;
	List<CategoryDto> getCategories();
}
