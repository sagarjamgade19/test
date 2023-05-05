package com.blogspots.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CategoryDto;
import com.blogspots.blog.payloads.UserDto;
import com.blogspots.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
    private CategoryService categoryServiceRepo;
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto categorySave=this.categoryServiceRepo.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categorySave,HttpStatus.CREATED);
		
	}
	//update Category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId) throws ResourceNotFoundException{
		
		CategoryDto categorySave=categoryServiceRepo.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categorySave,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?>deleteCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException{
		this.categoryServiceRepo.deleteCategory(categoryId);
		return new ResponseEntity(Map.of("Message","User Deleted Succesfully"),HttpStatus.OK);
		
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) throws ResourceNotFoundException{
		return ResponseEntity.ok(this.categoryServiceRepo.getCategory(categoryId)); 
	}
	
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(this.categoryServiceRepo.getCategories()); 
	}
	
	
}
