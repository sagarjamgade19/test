package com.blogspots.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogspots.blog.entity.Category;
import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CategoryDto;
import com.blogspots.blog.repository.CategoryRepo;
import com.blogspots.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.mapper.map(categoryDto, Category.class);
		Category addedCategory=categoryRepo.save(category);
		
		CategoryDto categoryDto2=this.mapper.map(addedCategory, CategoryDto.class);
		return categoryDto2;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) throws ResourceNotFoundException {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Record Not Found With given Id "+categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		categoryRepo.save(category);
		
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Record Not Found With given Id "+categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Record Not Found With given Id "+categoryId));
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category>categories=this.categoryRepo.findAll();
		
		return categories.stream().map((category)->mapper.map(categories, CategoryDto.class)).collect(Collectors.toList());
	}

}
