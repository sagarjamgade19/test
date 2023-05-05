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
import com.blogspots.blog.payloads.UserDto;
import com.blogspots.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService service;
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto)
	{
		 UserDto createdUser=this.service.createUser(dto);
		 return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto dto,@PathVariable int  userId) throws ResourceNotFoundException{
		UserDto updatedUser=this.service.updateUser(dto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?>deleteUser(@PathVariable int userId) throws ResourceNotFoundException{
		this.service.deleteUser(userId);
		return new ResponseEntity(Map.of("Message","User Deleted Succesfully"),HttpStatus.OK);
		
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.service.getAllUsers()); 
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) throws ResourceNotFoundException{
		return ResponseEntity.ok(this.service.getUserById(userId)); 
	}
}
