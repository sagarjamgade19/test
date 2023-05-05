package com.blogspots.blog.services;

import java.util.List;

import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto dto);
	UserDto updateUser(UserDto dto,int userId) throws ResourceNotFoundException;
	UserDto getUserById(int userId) throws ResourceNotFoundException;
	List<UserDto> getAllUsers();
	void deleteUser(int userId) throws ResourceNotFoundException;
}
